package com.cabegaira.aerolinea.logic

import java.io.Serializable

/*
at_id varchar2(45), year number, model varchar2(20),
brand varchar2(20), passengers_quantity number,
rows_number number, columns_number number)*/
class AirplaneType : Serializable {
        var at_id:String =""
        var model:String =""
        var brand:String =""
        var year:Int = 0
        var passengers_quantity:Int = 0
        var rows_number:Int = 0
        var columns_number:Int = 0

    constructor(
        at_id: String,
        model: String,
        brand: String,
        year: Int,
        passengers_quantity: Int,
        rows_number: Int,
        columns_number: Int
    ) {
        this.at_id = at_id
        this.model = model
        this.brand = brand
        this.year = year
        this.passengers_quantity = passengers_quantity
        this.rows_number = rows_number
        this.columns_number = columns_number
    }
}