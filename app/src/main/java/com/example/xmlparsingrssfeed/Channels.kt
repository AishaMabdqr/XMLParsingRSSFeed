package com.example.xmlparsingrssfeed

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable


@Root (name = "channel", strict = false)
class Channels constructor() : Serializable {

    @field:Element(name = "title")
    var title: String? = null


    @field:ElementList(required = false, inline= true, name = "item")
    var items: List<Items>? = null

}
