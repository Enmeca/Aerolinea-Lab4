package com.cabegaira.aerolinea.logic

/*
        id varchar2(20), airplane_type varchar2(45)
*/
class Airplane {
    var id:String=""
    //FIXME ver como vamos a manejar referencia
    var airplaneType:Int=0

    constructor(id: String, airplaneType: Int) {
        this.id = id
        this.airplaneType = airplaneType
    }
}