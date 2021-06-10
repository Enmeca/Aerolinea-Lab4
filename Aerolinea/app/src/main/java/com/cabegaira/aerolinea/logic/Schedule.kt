package com.cabegaira.aerolinea.logic

import java.io.Serializable


// id number not null,
//                         departure_time date, arrival_time date
class Schedule : Serializable {
    var id:Int=0
    var departure_time:String="0/0/0"
    var arrival_time:String="0/0/0"

    constructor(id: Int, departure_time: String, arrival_time: String) {
        this.id = id
        this.departure_time = departure_time
        this.arrival_time = arrival_time
    }
}