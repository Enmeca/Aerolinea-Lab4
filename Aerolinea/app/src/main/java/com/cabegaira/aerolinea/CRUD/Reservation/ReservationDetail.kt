package com.cabegaira.aerolinea.CRUD.Reservation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.cabegaira.aerolinea.CRUD.Tickets.TicketsSelection
import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Flight
import com.cabegaira.aerolinea.logic.Reservation

class ReservationDetail : AppCompatActivity() {

    var dato : Reservation? = null
    private var pos:Int = -1
    private var origenDestino: EditText? = null
    private var price: EditText? = null
    private var seats: EditText? = null
    private var date: EditText? = null


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_reservation)
        dato = intent.getSerializableExtra("dato") as Reservation
        origenDestino = findViewById<View>(R.id.txtTripView)  as EditText
        origenDestino!!.setText(dato!!.ruta)
        price = findViewById<View>(R.id.txtPrecioView)  as EditText
        price!!.setText(dato!!.totalPrice.toString())
        seats = findViewById<View>(R.id.txtAsientosView)  as EditText
        seats!!.setText(dato!!.seatQuantity.toString())
        date = findViewById<View>(R.id.tvFecha)  as EditText
        date!!.setText(dato!!.date)


    }
}