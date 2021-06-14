package com.cabegaira.aerolinea.CRUD.Flight

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.cabegaira.aerolinea.CRUD.Tickets.TicketsSelection
import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Flight

class FlightDetail : AppCompatActivity() {

    var dato : Flight? = null
    private var pos:Int = -1
    private var origenDestino: EditText? = null
    private var salida: EditText? = null
    private var llegada: EditText? = null
    private var price: EditText? = null
    private var seats: EditText? = null
    private var btn : Button? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_flights)
        dato = intent.getSerializableExtra("dato") as Flight
        btn = findViewById(R.id.btn_checkin) as Button
        origenDestino = findViewById<View>(R.id.txtTripView)  as EditText
        origenDestino!!.setText(dato!!.ruta)
        salida = findViewById<View>(R.id.txtSalidaView)  as EditText
        salida!!.setText(dato!!.departure_date)
        llegada = findViewById<View>(R.id.txtLlegadaView)  as EditText
        llegada!!.setText(dato!!.return_date)
        price = findViewById<View>(R.id.txtPrecioView)  as EditText
        price!!.setText(dato!!.price.toString())
        seats = findViewById<View>(R.id.txtAsientosView)  as EditText
        seats!!.setText(dato!!.available_seats.toString())

        btn!!.setOnClickListener{
            val i = Intent(this, TicketsSelection::class.java)
            i.putExtra("Ruta", origenDestino!!.text.toString())
            startActivity(i)
        }
    }
}