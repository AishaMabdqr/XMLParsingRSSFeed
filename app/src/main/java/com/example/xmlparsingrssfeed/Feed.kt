package com.example.xmlparsingrssfeed

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "rss", strict = false)
class Feed constructor(): Serializable{

    @field:Element ( name = "channel")
    var channel : Channels? = null


}