package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Route

class Routes {
    private var routes : ArrayList<Route> = ArrayList<Route>()
    private var filtradoroutes : ArrayList<Route> = ArrayList<Route>()
/*    init{
        addRoute(
            Route("Grecia","Costa Rica","Grecia","24/02/2022","9 Horas",
                        R.drawable.grecia
            )
        )
        //Spain
        addRoute(
            Route("satanas","Costa Rica","Spain","24/02/2022","9 Horas",
                R.drawable.grecia
            )
        )
    }*/

    private object HOLDER {
        val INSTANCE = Routes()
    }

    companion object {
        val instance: Routes by lazy{
            HOLDER.INSTANCE
        }
    }

    fun addRoute(app: Route){
        routes?.add(app)
    }

    fun getRoutes(): ArrayList<Route>{
        return this.routes!!
    }
    fun deleteRoutes(position: Int){
        routes!!.removeAt(position)
    }

    fun editRoute(position: Int, route: Route){
        routes!![position] = route

    }
    fun getRoute(position: Int) : Route {
        return this.routes!![position]
    }


    fun setFiltrado(origen:String, destino:String){

        filtradoroutes= routes.filter { x -> (x.destination==destino)&&(x.destination==origen) } as ArrayList<Route>

    }

    fun getRoutesFiltrado(): ArrayList<Route>{
        return this.filtradoroutes!!
    }

}