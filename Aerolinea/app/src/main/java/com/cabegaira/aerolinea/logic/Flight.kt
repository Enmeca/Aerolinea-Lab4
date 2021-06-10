package com.cabegaira.aerolinea.logic

import java.io.Serializable

// f_id number not null, ruta varchar2(20), departure_date date, 
//                       return_date date, price number, available_seats number)

class Flight : Serializable {

    var f_id:Int=0
    var ruta:String=""
    var departure_date:String=""
    var return_date:String=""
    var price:Int=0
    var available_seats:Int=0

    constructor(
        f_id: Int,
        ruta: String,
        departure_date: String,
        return_date: String,
        price: Int,
        available_seats: Int
    ) {
        this.f_id = f_id
        this.ruta = ruta
        this.departure_date = departure_date
        this.return_date = return_date
        this.price = price
        this.available_seats = available_seats
    }
}