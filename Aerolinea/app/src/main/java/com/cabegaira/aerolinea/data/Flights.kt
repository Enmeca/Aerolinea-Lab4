package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Flight

class Flights {
    private var flights : ArrayList<Flight> = ArrayList<Flight>()

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
                120,
                R.drawable.greeceflag
            )

        )
        addFlight(
            Flight(
                2,
                "Costa Rica - Alemania",
                "17/03/2021",
                "30/03/2021",
                1000,
                145,
                R.drawable.germanyflag
            )

        )
        addFlight(
            Flight(
                3,
                "Costa Rica - Japon",
                "20/04/2021",
                "30/04/2021",
                1200,
                130,
                R.drawable.japanflag
            )

        )
        addFlight(
            Flight(
                4,
                "Costa Rica - Estados Unidos",
                "17/03/2021",
                "30/03/2021",
                800,
                145,
                R.drawable.usaflag
            )

        )
        addFlight(
            Flight(
                5,
                "Costa Rica - Francia",
                "05/05/2021",
                "15/05/2021",
                1000,
                145,
                R.drawable.franceflag
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
        flights?.add(app)
    }

    fun getFlights(): ArrayList<Flight>{
        return this.flights!!
    }
    fun deleteFlights(position: Int){
        flights!!.removeAt(position)
    }

    fun editFlight(position: Int, Flight: Flight){
        flights!![position] = Flight

    }
    fun getFlight(position: Int) : Flight {
        return this.flights!![position]
    }
}