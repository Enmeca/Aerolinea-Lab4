package com.cabegaira.aerolinea.CRUD.Flight

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabegaira.aerolinea.CRUD.Reservation.CRUDReservation
import com.google.android.material.navigation.NavigationView
import java.util.*
import kotlin.collections.ArrayList

import com.cabegaira.aerolinea.adapters.RecyclerView_Adapter_Flight


import com.cabegaira.aerolinea.Login
import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Flight
import com.cabegaira.aerolinea.data.Flights
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class UserFlight : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    var INSTANCIA: Flights = Flights.instance
    var gson: Gson = Gson()
    var ws: WebSocket? = null
    var factory : WebSocketFactory? =null

    var origen:String=""
    var destiny:String=""

    lateinit var lista: RecyclerView
    lateinit var adaptador: RecyclerView_Adapter_Flight
    lateinit var ruta: Flight
    var archived = ArrayList<Flight>()
    var position: Int = 0
    val policy = StrictMode.ThreadPolicy.Builder()
        .permitAll()
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StrictMode.setThreadPolicy(policy)

        factory = WebSocketFactory().setConnectionTimeout(5000)

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


        destiny=intent.getSerializableExtra("destiny") as String

        origen=intent.getSerializableExtra("origen") as String

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

        try {
            ws = factory?.createSocket("ws://52.167.232.76:9393/controllerflights")

            ws!!.addListener( object: WebSocketAdapter(){
                override fun onTextMessage(websocket: WebSocket?, text: String?) {
                    super.onTextMessage(websocket, text)
                    Log.d("TESTE","ontextmesage"+text)
                    val jsonArray = JSONTokener(text).nextValue() as JSONArray
                    INSTANCIA.clearData()

                    val itemType = object : TypeToken<List<Flight>>(){}.type
                    var result: ArrayList<Flight> = gson.fromJson(text, itemType);



                    INSTANCIA.setData(result)
                    getListOfFlights()

                }

            })

            ws?.connect()

        }catch (e:Exception) {
            Log.d("Error", e.toString())
        }finally {
            sendMessage()
        }

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


        }



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
        var Nrutas = ArrayList<Flight>()
        for (p in INSTANCIA.getFlights()) {
            Nrutas.add(p)
        }
        Nrutas=Nrutas.filter { x -> (x.route?.destination==destiny)&&(x.route?.origin==origen) } as ArrayList<Flight>
        adaptador = RecyclerView_Adapter_Flight(Nrutas)
        adaptador.notifyDataSetChanged()
        lista.adapter = adaptador
    }
    fun sendMessage(){
        if(ws?.isOpen()==true){
            Log.d("boton","entra")
            val x = JSONObject()
            x.put("action","flights-list")
            ws?.sendText(x.toString())
        }else{
            Log.d("boton","no entro")
        }
    }
}

