package com.example.xmlparsingrssfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var rvItems : RecyclerView
    lateinit var itemList : ArrayList<String>
    lateinit var rvAdapter: RVAdapter

    private  val TAG = "MainActivity"
    private  val BASE_URL = "https://rss.nytimes.com/services/xml/rss/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvItems = findViewById(R.id.rvItems)
        itemList = ArrayList()
        rvItems = findViewById(R.id.rvItems)

        rvAdapter = RVAdapter(itemList)
        rvItems.adapter = rvAdapter
        rvItems.layoutManager = LinearLayoutManager(this)

        getItems()


    }

    fun getItems(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        val feedAPI = retrofit.create(FeedAPI::class.java)
        val call = feedAPI.feed

        call!!.clone().enqueue(object : Callback<Feed?> {
            override fun onResponse(call: Call<Feed?>, response: Response<Feed?>) {
                Log.d(TAG, "onResponse: feed: " + response.body().toString())
                Log.d(TAG, "onResponse: Server Response: $response")
                var result = ""
                val entries = response.body()!!.channel!!.items
                for (entry in entries!!) {
                   result = "Title : "+entry.title+"\n Description: "+entry.description
                    itemList.add(result)
                }

                rvAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Feed?>, t: Throwable) {
                Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.message)
                Toast.makeText(this@MainActivity, "An Error Occured", Toast.LENGTH_SHORT).show()
            }
        })

    }
}