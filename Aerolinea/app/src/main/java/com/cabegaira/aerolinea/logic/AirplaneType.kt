package com.cabegaira.aerolinea.logic

import java.io.Serializable

/*
at_id varchar2(45), year number, model varchar2(20),
brand varchar2(20), passengers_quantity number,
rows_number number, columns_number number)*/
class AirplaneType : Serializable {
        var id:String =""
        var model:String =""
        var brand:String =""
        var year:Int = 0
        var passengersQuantity:Int = 0
        var rowsNumber:Int = 0
        var columnsNumber:Int = 0
    /*
    *
    *         this.id = id;
        this.year = year;
        this.model = model;
        this.brand = brand;
        this.passengersQuantity = passengersQuantity;
        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;
    * */

//{"id":"Airbus 320","year":2000,"model":"320","brand":"Airbus",
//"passengersQuantity":132,"rowsNumber":6,"columnsNumber":22}
    constructor(
        id: String,
        year: Int,
        model: String,
        brand: String,
        passengers_quantity: Int,
        rowsNumber: Int,
        columnsNumber: Int
    ) {
        this.id = id
        this.model = model
        this.brand = brand
        this.year = year
        this.passengersQuantity = passengers_quantity
        this.rowsNumber = rowsNumber
        this.columnsNumber = columnsNumber
    }
}