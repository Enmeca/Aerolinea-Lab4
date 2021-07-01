package com.cabegaira.aerolinea.CRUD.Airplane


//import com.neovisionaries.ws.client.WebSocket

import android.content.Intent
import android.graphics.Canvas
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
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabegaira.aerolinea.Login
import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.adapters.RecyclerView_Adapter_Airplane
import com.cabegaira.aerolinea.data.Airplanes
import com.cabegaira.aerolinea.logic.Airplane
import com.cabegaira.aerolinea.logic.AirplaneType
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import okhttp3.*
import okio.ByteString
import org.json.JSONObject
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class CRUDAirplane : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    var INSTANCIA: Airplanes = Airplanes.instance

/*    var ws: WebSocket? = null
    var factory : WebSocketFactory? =null*/
    var gson: Gson = Gson()
    private val client: OkHttpClient? = null
        lateinit var lista: RecyclerView
        lateinit var adaptador: RecyclerView_Adapter_Airplane
        lateinit var ruta: Airplane
        var archived = ArrayList<Airplane>()
        var position: Int = 0

    val policy = StrictMode.ThreadPolicy.Builder()
        .permitAll()
        .build()


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            //EMC NetworkOnMain
            //StrictMode.setThreadPolicy(policy)

            setContentView(R.layout.list_airplanes_activity)

           // factory = WebSocketFactory().setConnectionTimeout(5000)
/*
            val request = Request.Builder().url("ws://echo.websocket.org").build()
            val listener = EchoWebSocketListener()
            val ws: WebSocket = client!!.newWebSocket(request, listener)
            client.dispatcher().executorService().shutdown()*/

            // wss test
            val client = OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                //.sslSocketFactory() - ? нужно ли его указывать дополнительно
                .build()
            val request = Request.Builder()
                .url("ws://52.167.232.76:9393/controllertypes") // 'wss' - для защищенного канала
                .build()
            val wsListener = EchoWebSocketListener ()
            val webSocket = client.newWebSocket(request, wsListener) // this provide to make 'Open ws connection'



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

            findViewById<SearchView>(R.id.airplane_search).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adaptador.filter.filter(newText)
                    return false
                }
            })


            /*try {
                ws = factory?.createSocket("ws://52.167.232.76:9393/controllertypes")


                ws?.connect()

            }catch (e:Exception) {
                Log.d("errorcito", e.toString())
            }finally {
                sendMessage()
            }
            ws?.addListener( object: WebSocketAdapter(){
                override fun onTextMessage(websocket: WebSocket?, text: String?) {
                    super.onTextMessage(websocket, text)
                    Log.d("TESTE","ontextmesage"+text)
                    val jsonArray = JSONTokener(text).nextValue() as JSONArray
                    INSTANCIA.clearList()

                    val itemType = object : TypeToken<List<Airplane>>(){}.type
                    var result: ArrayList<Airplane> = gson.fromJson(text, itemType);
                    INSTANCIA.getData(result)
*//*                    for (i in 0 until jsonArray.length()){
                        val svtype:AirplaneType = AirplaneType(
                        jsonArray.getJSONObject(i).getString("id"),
                        jsonArray.getJSONObject(i).getInt("year"),
                        jsonArray.getJSONObject(i).getString("model"),
                        jsonArray.getJSONObject(i).getString("brand"),
                        jsonArray.getJSONObject(i).getInt("passengersQuantity"),
                        jsonArray.getJSONObject(i).getInt("rowsNumber"),
                        jsonArray.getJSONObject(i).getInt("columnsNumber")
                        )
                        types.addAirplaneType(svtype)

                    }*//*
                    Log.d("LIST",result.toString())
                }

            })*/

            //Log.d("LISTENER", ws.ist)
            getListOfAirplanes()

            val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    val fromPosition: Int = viewHolder.adapterPosition
                    val toPosition: Int = target.adapterPosition

                    Collections.swap(INSTANCIA.getAirplanes(), fromPosition, toPosition)

                    lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                    return false
                }
                /*
                * FIXME
                *  poner el swipe para el usuario
                * */
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    position = viewHolder.adapterPosition
                    var quien: String = ""


                    if(direction == ItemTouchHelper.LEFT){
/*                        ruta = Airplane(INSTANCIA.getAirplanes()[position], INSTANCIA.getAirplanes()[position].lastName,
                            jobs.getApplications()[position].streetAddress1, jobs.getApplications()[position].streetAddress2, jobs.getApplications()[position].city, jobs.getApplications()[position].state, jobs.getApplications()[position].postal, jobs.getApplications()[position].country, jobs.getApplications()[position].email, jobs.getApplications()[position].areaCode, jobs.getApplications()[position].phone, jobs.getApplications()[position].position, jobs.getApplications()[position].date)
                        jobs.deleteJob(position)
                        lista.adapter?.notifyItemRemoved(position)

                        Snackbar.make(lista, aplication.firstName + "Se eliminaría... ", Snackbar.LENGTH_LONG).setAction("Undo") {
                            jobs.getApplications().add(position, aplication)
                            lista.adapter?.notifyItemInserted(position)
                        }.show()
                        adaptador = RecyclerView_Adapter(jobs.getApplications())
                        lista.adapter = adaptador
                        adaptador.notifyAirplanesSetChanged()*/
                    }else{
                        /*val intent = Intent(this@CRUDJobs, EditAplication::class.java)
                        val item = jobs.getJob(position)
                        intent.putExtra("dato", item )
                        intent.putExtra("position",position)
                        startActivity(intent)
                        adaptador.notifyAirplanesSetChanged()
                        //getListOfPersons()*/
                    }
                }

                override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                    RecyclerViewSwipeDecorator.Builder(this@CRUDAirplane, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(
                            ContextCompat.getColor(this@CRUDAirplane,
                            R.color.Ruby_Red
                        ))
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                        .addSwipeRightBackgroundColor(
                            ContextCompat.getColor(this@CRUDAirplane,
                            R.color.Viridian_Green))
                        .addSwipeRightActionIcon(R.drawable.ic_baseline_edit_24)
                        .create()
                        .decorate()
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }

            }



            val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
            itemTouchHelper.attachToRecyclerView(lista)


        }

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId){
                R.id.nav_logout -> {
                    Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, Login::class.java)
                    startActivity(i)
                    finish()
                }
            }
            return true
        }

    private fun start() {
        val request = Request.Builder().url("ws://echo.websocket.org").build()
        val listener = EchoWebSocketListener()
        val ws: WebSocket = client!!.newWebSocket(request, listener)
        client.dispatcher().executorService().shutdown()
    }


        private fun getListOfAirplanes() {
            val Nrutas = ArrayList<Airplane>()
            for (p in INSTANCIA.getAirplanes()) {
                Nrutas.add(p)
            }
            adaptador = RecyclerView_Adapter_Airplane(Nrutas)
            lista.adapter = adaptador
        }

/*    fun sendMessage(){
        if(ws?.isOpen()==true){
            Log.d("boton","entra")

            ws?.sendText(x.toString())
        }else{
            Log.d("boton","no entro")
        }
    }*/

    private class EchoWebSocketListener : WebSocketListener() { //Aqui escuchamos los cambios
        var gson:Gson = Gson()

        override fun onOpen(webSocket: WebSocket, response: Response) {
            val x = JSONObject()
            x.put("action","planes-list")
            webSocket.send(x.toString())
        }

        override fun onMessage(webSocket: WebSocket?, text: String?) {  //El nuestro
            val itemType = object : TypeToken<ArrayList<AirplaneType>>(){}.type
            var result: List<AirplaneType> = gson.fromJson(text, itemType);
            Log.d("MENSAJE", text.toString())
            output("Receiving : " + text!!)
        }

        override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
            output("Receiving bytes : " + bytes!!.hex())
        }

        override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
            webSocket!!.close(NORMAL_CLOSURE_STATUS, null)
            output("Closing : $code / $reason")
        }

        companion object {
            private val NORMAL_CLOSURE_STATUS = 1000
        }

        private fun output(txt: String) {
            Log.v("WSS", txt)
        }
    }


}