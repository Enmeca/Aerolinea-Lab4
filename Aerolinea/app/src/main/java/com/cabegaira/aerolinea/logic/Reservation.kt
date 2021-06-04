package com.cabegaira.aerolinea.logic

// res_id number not null, userid number, totalPrice number, seatQuantity number) tablespace system; 


class Reservation {
    var res_id:Int=0
    var User_id:Int=0
    var totalPrice:Int=0
    var seatQuantity:Int=0

    constructor(res_id: Int, User_id: Int, totalPrice: Int, seatQuantity: Int) {
        this.res_id = res_id
        this.User_id = User_id
        this.totalPrice = totalPrice
        this.seatQuantity = seatQuantity
    }
}