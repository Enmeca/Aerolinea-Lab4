package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Country

class Countries {
    private var Countries : ArrayList<Country> = ArrayList<Country>()

//    var name:String = ""
//    var id:String = ""

    init{
        addCountry(Country(
                "Costa Rica",
                "CR"
            ))
    }

    private object HOLDER {
        val INSTANCE = Countries()
    }

    companion object {
        val instance: Countries by lazy{
            HOLDER.INSTANCE
        }
    }

    fun addCountry(app: Country){
        Countries?.add(app)
    }

    fun getCountries(): ArrayList<Country>{
        return this.Countries!!
    }
    fun deleteCountries(position: Int){
        Countries!!.removeAt(position)
    }

    fun editCountry(position: Int, Country: Country){
        Countries!![position] = Country

    }
    fun getCountry(position: Int) : Country {
        return this.Countries!![position]
    }
}