package com.cabegaira.aerolinea.logic

// create table ticket(ticket_id number not null, fila number, 
// col number, reservation number, flight number) tablespace system;

class Ticket {

    var ticket_id:Int=0
    var fila:Int=0
    var col:Int=0
    var reservation:Int=0
    var flight:Int=0

    constructor(ticket_id: Int, fila: Int, col: Int, reservation: Int, flight: Int) {
        this.ticket_id = ticket_id
        this.fila = fila
        this.col = col
        this.reservation = reservation
        this.flight = flight
    }
}