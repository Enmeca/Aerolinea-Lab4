package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Flight

class Flights {
    private var Flights : ArrayList<Flight> = ArrayList<Flight>()

    init{
        addFlight(
//            f_id: Int,
//            ruta: String,
//            departure_date: String,
//            return_date: String,
//            price: Int,
//            available_seats: Int
            Flight(
                1,
                "Costa Rica - Grecia",
                "24/02/2021",
                "13/05/2021",
                650,
                120
            )
        )
    }

    private object HOLDER {
        val INSTANCE = Flights()
    }

    companion object {
        val instance: Flights by lazy{
            HOLDER.INSTANCE
        }
    }

    fun addFlight(app: Flight){
        Flights?.add(app)
    }

    fun getFlights(): ArrayList<Flight>{
        return this.Flights!!
    }
    fun deleteFlights(position: Int){
        Flights!!.removeAt(position)
    }

    fun editFlight(position: Int, Flight: Flight){
        Flights!![position] = Flight

    }
    fun getFlight(position: Int) : Flight {
        return this.Flights!![position]
    }
}