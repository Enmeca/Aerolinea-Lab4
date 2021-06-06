package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.AirplaneType

class Airtypes {
    private var AirplaneTypes : ArrayList<AirplaneType> = ArrayList<AirplaneType>()

    init{
/*
        at_id: String,
        model: String,
        brand: String,
        year: Int,
        passengers_quantity: Int,
        rows_number: Int,
        columns_number: Int*/

        addAirplaneType(
            AirplaneType(
                "666-TI",
                "Boing",
                "Patitos",
                2008,
                120,
                6,
                20
            )
        )
    }

    private object HOLDER {
        val INSTANCE = Airtypes()
    }

    companion object {
        val instance: Airtypes by lazy{
            HOLDER.INSTANCE
        }
    }

    fun addAirplaneType(app: AirplaneType){
        AirplaneTypes?.add(app)
    }

    fun getAirplaneTypes(): ArrayList<AirplaneType>{
        return this.AirplaneTypes!!
    }
    fun deleteAirplaneTypes(position: Int){
        AirplaneTypes!!.removeAt(position)
    }

    fun editAirplaneType(position: Int, AirplaneType: AirplaneType){
        AirplaneTypes!![position] = AirplaneType

    }
    fun getAirplaneType(position: Int) : AirplaneType {
        return this.AirplaneTypes!![position]
    }
}