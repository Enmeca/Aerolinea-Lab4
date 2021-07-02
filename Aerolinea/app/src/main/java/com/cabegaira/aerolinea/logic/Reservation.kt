package com.cabegaira.aerolinea.logic

import java.io.Serializable

// res_id number not null, userid number, totalPrice number, seatQuantity number) tablespace system; 
/*
    private int id;
    private User user;
    private float totalPrice;
    private int seatQuantity;

 */
/*

        this.route = route;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;

 */
class Reservation : Serializable {
    var id:Int=0
    var user:String=""
    var totalPrice:Int= 0
    var seatQuantity:Int=0
    var route: Route?=null
    var departureDate:String=""
    var returnDate:String=""
    var price:Int=0

    constructor(
        id: Int,
        user: String,
        totalPrice: Int,
        seatQuantity: Int,
        route: Route?,
        departureDate: String,
        returnDate: String,
        price: Int
    ) {
        this.id = id
        this.user = user
        this.totalPrice = totalPrice
        this.seatQuantity = seatQuantity
        this.route = route
        this.departureDate = departureDate
        this.returnDate = returnDate
        this.price = price
    }

}