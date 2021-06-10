package com.cabegaira.aerolinea.logic

import java.io.Serializable

class Country : Serializable {
    var name:String = ""
    var id:String = ""

    constructor(name: String, id: String) {
        this.name = name
        this.id = id
    }
}