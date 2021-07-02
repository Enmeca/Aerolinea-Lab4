package com.cabegaira.aerolinea.logic

import java.io.Serializable


class Route : Serializable {

    var id: String? = null
    var origin: String? = null
    var destination: String? = null

    constructor(id: String?, origin: String?, destination: String?) {
        this.id = id
        this.origin = origin
        this.destination = destination
    }
}