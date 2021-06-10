package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.City

class Cities {
    private var Cities : ArrayList<City> = ArrayList<City>()

    init{
//        name: String, Id: String, country: String

        addCity(
            City(
                "San Jose",
                "SJO",
                "Costa Rica"
            )
        )

        addCity(
            City(
                "California",
                "CAL",
                "Estados Unidos"
            )
        )

    }

    private object HOLDER {
        val INSTANCE = Cities()
    }

    companion object {
        val instance: Cities by lazy{
            HOLDER.INSTANCE
        }
    }

    fun addCity(app: City){
        Cities?.add(app)
    }

    fun getCities(): ArrayList<City>{
        return this.Cities!!
    }
    fun deleteCities(position: Int){
        Cities!!.removeAt(position)
    }

    fun editCity(position: Int, City: City){
        Cities!![position] = City

    }
    fun getCity(position: Int) : City {
        return this.Cities!![position]
    }
}