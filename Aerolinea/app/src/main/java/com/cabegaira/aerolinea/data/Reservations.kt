package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Reservation

class Reservations {
    private var Reservations : ArrayList<Reservation> = ArrayList<Reservation>()

    init{
//        res_id: Int, User_id: Int, totalPrice: Int, seatQuantity: Int)
        addReservation(
            Reservation(
                1,
                117390080,
                2000,
                2
            )
        )
    }

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