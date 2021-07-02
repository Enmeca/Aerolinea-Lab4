package com.cabegaira.aerolinea.CRUD.Rutas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent

import com.cabegaira.aerolinea.logic.Route
import com.cabegaira.aerolinea.data.Routes

import com.cabegaira.aerolinea.R

import android.app.DatePickerDialog.OnDateSetListener
import android.app.DatePickerDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*

import android.widget.AutoCompleteTextView
import android.widget.EditText

import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.DatePicker
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cabegaira.aerolinea.CRUD.Flight.UserFlight
import com.cabegaira.aerolinea.Login
import java.util.*

class SearchRoute : AppCompatActivity(){

    var rutas: Routes = Routes.instance

    private lateinit var etOrigin: AutoCompleteTextView
    private lateinit var etDestiny: AutoCompleteTextView

    private var etName: EditText? = null
    private var etDuration: EditText? = null
    private var etDate: EditText? = null

    private var etfoto: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_route_activity)

        etOrigin = findViewById<AutoCompleteTextView>(R.id.spinnerSalida)
        etDestiny = findViewById<AutoCompleteTextView>(R.id.spinnerLlegado)

/*        etName = findViewById<View>(R.id.txtName) as EditText
        etDuration = findViewById<View>(R.id.txtDuracion) as EditText

        etDate = findViewById<View>(R.id.dataPicker) as EditText
        etDate!!.setOnClickListener { showDateDialog(etDate) }*/

        val countries = arrayOf(
            "Costa Rica",
            "Alemania",
            "Estados Unidos",
            "Rusia",
            "Japon",
            "Grecia",
            "China"
        )

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        etDestiny.setAdapter(adapter)
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        etOrigin.setAdapter(adapter)
    }

    fun send(view: View?) {

        /*val name = etName!!.text.toString()*/
        val origin = etOrigin!!.text.toString()
        val destiny = etDestiny!!.text.toString()
/*        val exitDate = etDate!!.text.toString()
        val duration = etDuration!!.text.toString()
        val ruta = Route(
            name,
            origin,
            destiny,
            exitDate,
            duration,
            R.drawable.banner_main
        )*/
        rutas.setFiltrado(origin, destiny)
        val i = Intent(this, UserFlight::class.java)
        i.putExtra("origen",origin)
        i.putExtra("destiny",destiny)
        startActivity(i)
        finish()
        //Toast.makeText(this, "Añadido Exitosamente", Toast.LENGTH_SHORT).show()
    }


    // --------------------------DatePicker---------------------
    private fun showDateDialog(date_in: EditText?) {
        val calendar = Calendar.getInstance()
        val dateSetListener = OnDateSetListener { view, year, month, dayOfMonth ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            date_in!!.setText(simpleDateFormat.format(calendar.time))
        }
        DatePickerDialog(
            this@SearchRoute,
            dateSetListener,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }
}