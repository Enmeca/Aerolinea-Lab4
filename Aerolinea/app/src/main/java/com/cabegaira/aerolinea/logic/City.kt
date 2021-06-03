package com.cabegaira.aerolinea.logic

class City {
    var name:String = ""
    var Id:String = ""
    //FIXME ver si agregar la referencia como int para bucar la pos o el objeto
    var country:String = ""

    constructor(name: String, Id: String, country: String) {
        this.name = name
        this.Id = Id
        this.country = country
    }

}