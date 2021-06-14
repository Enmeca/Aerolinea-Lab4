package com.cabegaira.aerolinea.logic

import java.io.Serializable

// res_id number not null, userid number, totalPrice number, seatQuantity number) tablespace system; 


class Reservation : Serializable {
    var res_id:Int=0
    var user:String=""
    var totalPrice:Int=0
    var seatQuantity:Int=0
    var ruta:String=""
    var date:String=""

    constructor(res_id: Int, user: String, totalPrice: Int, seatQuantity: Int, ruta : String, date : String) {
        this.res_id = res_id
        this.user = user
        this.totalPrice = totalPrice
        this.seatQuantity = seatQuantity
        this.ruta = ruta
        this.date = date
    }
}