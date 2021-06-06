package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Ticket

class Tickets {
    private var Tickets : ArrayList<Ticket> = ArrayList<Ticket>()

//    ticket_id: Int, fila: Int, col: Int, reservation: Int, flight: Int
    init{
        addTicket(
            Ticket(
                1,
                2,
                1,
                1,
                1
            )
        )
    }

    private object HOLDER {
        val INSTANCE = Tickets()
    }

    companion object {
        val instance: Tickets by lazy{
            HOLDER.INSTANCE
        }
    }

    fun addTicket(app: Ticket){
        Tickets?.add(app)
    }

    fun getTickets(): ArrayList<Ticket>{
        return this.Tickets!!
    }
    fun deleteTickets(position: Int){
        Tickets!!.removeAt(position)
    }

    fun editTicket(position: Int, Ticket: Ticket){
        Tickets!![position] = Ticket

    }
    fun getTicket(position: Int) : Ticket {
        return this.Tickets!![position]
    }
}