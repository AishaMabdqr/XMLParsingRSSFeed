package com.example.xmlparsingrssfeed

import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text
import java.io.Serializable


@Root (name = "item", strict = false)
class Items @JvmOverloads constructor(

    @field:Element(required = false, name = "title")
    @param:Element(name = "title")
    var title: String? = null,


//    @field:Element( required = false, name = "description")
//    @param:Element(name = "description")
//    @Path ("rss/channel/item")
//    var description: String? = null

    @field:Path("description[1]")
    @field:Text()
    var description: String? = null



) : Serializable {
}