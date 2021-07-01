package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Airplane

class Airplanes {
    private var Airplanes : ArrayList<Airplane> = ArrayList<Airplane>()

//    this.id = id
//    this.airplaneType = airplaneType

    init{
/*        addAirplane(
            Airplane(
                "TI-666",
                1
            )
        )*/
    }

    private object HOLDER {
        val INSTANCE = Airplanes()
    }

    companion object {
        val instance: Airplanes by lazy{
            HOLDER.INSTANCE
        }
    }

    fun addAirplane(app: Airplane){
        Airplanes?.add(app)
    }

    fun getAirplanes(): ArrayList<Airplane>{
        return this.Airplanes!!
    }
    fun deleteAirplanes(position: Int){
        Airplanes!!.removeAt(position)
    }

    fun editAirplane(position: Int, Airplane: Airplane){
        Airplanes!![position] = Airplane

    }
    fun getAirplane(position: Int) : Airplane {
        return this.Airplanes!![position]
    }

    fun clearList(){
        Airplanes.clear()
    }

    fun getData(Lista:ArrayList<Airplane>){
        Airplanes.clear()
        Airplanes=Lista
    }
}