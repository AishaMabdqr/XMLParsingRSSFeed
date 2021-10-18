package com.example.xmlparsingrssfeed

import retrofit2.Call
import retrofit2.http.GET

interface FeedAPI {
    @get:GET ("nyt/Technology.xml")
    val feed : Call<Feed>?

    companion object {
        const val BASE_URL = "https://rss.nytimes.com/services/xml/rss/"
    }
}