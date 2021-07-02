package com.cabegaira.aerolinea.logic

import com.cabegaira.aerolinea.R
import java.io.Serializable

// f_id number not null, ruta varchar2(20), departure_date date, 
//                       return_date date, price number, available_seats number)

class Flight : Serializable {

    var id:Int=0
    var route:Route? = null
    var departureDate:String=""
    var returnDate:String=""
    var price:Int=0
    var availableSeats:Int=0
    var plane:Airplane?=null
    var Foto:Int = 0


    constructor(
        id: Int,
        route: Route?,
        departureDate: String,
        returnDate: String,
        price: Int,
        availableSeats: Int,
        plane: Airplane?
    ) {
        this.id = id
        this.route = route
        this.departureDate = departureDate
        this.returnDate = returnDate
        this.price = price
        this.availableSeats = availableSeats
        this.plane = plane
        this.Foto = R.drawable.default_country
    }
}