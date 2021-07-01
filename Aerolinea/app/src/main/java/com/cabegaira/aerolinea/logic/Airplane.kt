package com.cabegaira.aerolinea.logic

import java.io.Serializable

/*
        id varchar2(20), airplane_type varchar2(45)
*/
class Airplane : Serializable {
    var id:String=""
    //FIXME ver como vamos a manejar referencia
    var type:AirplaneType? = null

    constructor(id: String, type: AirplaneType) {
        this.id = id
        this.type = type
    }
}