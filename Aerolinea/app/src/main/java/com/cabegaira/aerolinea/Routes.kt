package com.cabegaira.aerolinea

class Routes {
    private var routes : ArrayList<Route> = ArrayList<Route>()

    init{
        addRoute(Route("Grecia","Costa Rica","Grecia","24/02/2022","9 Horas",
                        R.drawable.grecia))
    }

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

    fun editJob(position: Int, route: Route){
        routes!![position] = route

    }
    fun getJob(position: Int) : Route{
        return this.routes!![position]
    }
}