package com.cabegaira.aerolinea.CRUD.Flight

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabegaira.aerolinea.CRUD.Reservation.CRUDReservation
import com.google.android.material.navigation.NavigationView
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*
import kotlin.collections.ArrayList

import com.cabegaira.aerolinea.adapters.RecyclerView_Adapter_Flight


import com.cabegaira.aerolinea.Login
import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Flight
import com.cabegaira.aerolinea.data.Flights

class UserFlight : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    var INSTANCIA: Flights = Flights.instance

    lateinit var lista: RecyclerView
    lateinit var adaptador: RecyclerView_Adapter_Flight
    lateinit var ruta: Flight
    var archived = ArrayList<Flight>()
    var position: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.list_flights_activity)

        val navView: NavigationView = findViewById(R.id.nav_view)

        val searchIcon = findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)

        val cancelIcon = findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)

        val textView = findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.BLACK)

        lista = findViewById(R.id.lista)
        lista.layoutManager = LinearLayoutManager(lista.context)
        lista.setHasFixedSize(true)


        navView.setNavigationItemSelectedListener(this)

        findViewById<SearchView>(R.id.flight_search).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adaptador.filter.filter(newText)
                return false
            }
        })

        getListOfFlights()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition: Int = viewHolder.adapterPosition
                val toPosition: Int = target.adapterPosition

                Collections.swap(INSTANCIA.getFlights(), fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                TODO("Not yet implemented")
            }
            /*
            * FIXME
            *  poner el swipe para el usuario
            * */

           /* override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                position = viewHolder.adapterPosition
                var quien: String = ""


                if(direction == ItemTouchHelper.LEFT){
/*                        ruta = Flight(INSTANCIA.getFlights()[position], INSTANCIA.getFlights()[position].lastName,
                            jobs.getApplications()[position].streetAddress1, jobs.getApplications()[position].streetAddress2, jobs.getApplications()[position].city, jobs.getApplications()[position].state, jobs.getApplications()[position].postal, jobs.getApplications()[position].country, jobs.getApplications()[position].email, jobs.getApplications()[position].areaCode, jobs.getApplications()[position].phone, jobs.getApplications()[position].position, jobs.getApplications()[position].date)
                        jobs.deleteJob(position)
                        lista.adapter?.notifyItemRemoved(position)

                        Snackbar.make(lista, aplication.firstName + "Se eliminaría... ", Snackbar.LENGTH_LONG).setAction("Undo") {
                            jobs.getApplications().add(position, aplication)
                            lista.adapter?.notifyItemInserted(position)
                        }.show()
                        adaptador = RecyclerView_Adapter(jobs.getApplications())
                        lista.adapter = adaptador
                        adaptador.notifyFlightsSetChanged()*/
                }else{
                    /*val intent = Intent(this@CRUDJobs, EditAplication::class.java)
                    val item = jobs.getJob(position)
                    intent.putExtra("dato", item )
                    intent.putExtra("position",position)
                    startActivity(intent)
                    adaptador.notifyFlightsSetChanged()
                    //getListOfPersons()*/
                }
            }*/

            /*override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                RecyclerViewSwipeDecorator.Builder(this@UserFlight, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(
                        ContextCompat.getColor(this@UserFlight,
                            R.color.Ruby_Red
                        ))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeRightBackgroundColor(
                        ContextCompat.getColor(this@UserFlight,
                            R.color.Viridian_Green))
                    .addSwipeRightActionIcon(R.drawable.ic_baseline_edit_24)
                    .create()
                    .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }*/

        }



        /*val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(lista)*/


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_vuelos -> {
                Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show()
                val i = Intent(this, UserFlight::class.java)
                startActivity(i)
            }
            R.id.nav_viajes -> {
                Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show()
                val i = Intent(this, CRUDReservation::class.java)
                startActivity(i)
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show()
                val i = Intent(this, Login::class.java)
                startActivity(i)
                finish()
            }
        }
        return true
    }

    private fun getListOfFlights() {
        val Nrutas = ArrayList<Flight>()
        for (p in INSTANCIA.getFlights()) {
            Nrutas.add(p)
        }
        adaptador = RecyclerView_Adapter_Flight(Nrutas)
        lista.adapter = adaptador
    }

}

