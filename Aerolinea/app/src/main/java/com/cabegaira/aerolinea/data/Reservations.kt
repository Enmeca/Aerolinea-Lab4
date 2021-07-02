package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Reservation

class Reservations {
    private var Reservations : ArrayList<Reservation> = ArrayList<Reservation>()

/*    init{
//        res_id: Int, User_id: Int, totalPrice: Int, seatQuantity: Int)
        addReservation(
            Reservation(
                1,
                "Philippe Gairaud",
                2500,
                2,
                "Costa Rica-Estados Unidos",
                "24-03-2021/30-03-2021"
            )
        )
        addReservation(
            Reservation(
                2,
                "Philippe Gairaud",
                3000,
                4,
                "Costa Rica-Japon",
                "10-05-2021/20-05-2021"
            )
        )
        addReservation(
            Reservation(
                3,
                "Philippe Gairaud",
                1600,
                2,
                "Costa Rica-Alemania",
                "15-06-2021/20-06-2021"
            )
        )
        addReservation(
            Reservation(
                4,
                "Philippe Gairaud",
                2000,
                3,
                "Costa Rica-Grecia",
                "15-07-2021/25-07-2021"
            )
        )
    }*/

    private object HOLDER {
        val INSTANCE = Reservations()
    }

    companion object {
        val instance: Reservations by lazy{
            HOLDER.INSTANCE
        }
    }

    fun addReservation(app: Reservation){
        Reservations?.add(app)
    }

    fun getReservations(): ArrayList<Reservation>{
        return this.Reservations!!
    }
    fun deleteReservations(position: Int){
        Reservations!!.removeAt(position)
    }

    fun editReservation(position: Int, Reservation: Reservation){
        Reservations!![position] = Reservation

    }
    fun getReservation(position: Int) : Reservation {
        return this.Reservations!![position]
    }
}