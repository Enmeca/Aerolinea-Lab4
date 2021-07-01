package com.cabegaira.aerolinea.CRUD.AirplaneType


import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabegaira.aerolinea.Login
import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.adapters.RecyclerView_Adapter_AirplaneType
import com.cabegaira.aerolinea.data.Airtypes
import com.cabegaira.aerolinea.logic.AirplaneType
import com.cabegaira.aerolinea.websockets.Teste
import com.google.android.material.navigation.NavigationView
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.util.*
import kotlin.collections.ArrayList


class CRUDAirType : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{


    var INSTANCIA: Airtypes = Airtypes.instance
    var ws: WebSocket? = null
    var factory : WebSocketFactory? =null


    lateinit var lista: RecyclerView
    lateinit var adaptador: RecyclerView_Adapter_AirplaneType
    lateinit var ruta: AirplaneType
    var archived = ArrayList<AirplaneType>()
    var position: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder()
            .permitAll()
            .build()
        StrictMode.setThreadPolicy(policy)

        factory = WebSocketFactory().setConnectionTimeout(5000)


        setContentView(R.layout.list_airtype_activity)

/*        lifecycleScope.launch {

            withContext(Dispatchers.IO){Teste.getData()}

        }*/

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

        findViewById<SearchView>(R.id.airtype_search).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adaptador.filter.filter(newText)
                return false
            }
        })
        try {
            ws = factory?.createSocket("ws://52.167.232.76:9393/controllertypes")

            ws?.addListener( object: WebSocketAdapter(){
                override fun onTextMessage(websocket: WebSocket?, text: String?) {
                    super.onTextMessage(websocket, text)
                    Log.d("TESTE","ontextmesage"+text)
                    val jsonArray = JSONTokener(text).nextValue() as JSONArray
                    INSTANCIA.clearList()

                    for (i in 0 until jsonArray.length()){
                        val svtype: AirplaneType = AirplaneType(
                            jsonArray.getJSONObject(i).getString("id"),
                            jsonArray.getJSONObject(i).getInt("year"),
                            jsonArray.getJSONObject(i).getString("model"),
                            jsonArray.getJSONObject(i).getString("brand"),
                            jsonArray.getJSONObject(i).getInt("passengersQuantity"),
                            jsonArray.getJSONObject(i).getInt("rowsNumber"),
                            jsonArray.getJSONObject(i).getInt("columnsNumber")
                        )
                        INSTANCIA.addAirplaneType(svtype)

                    }
                    getListOfAirtypes()
                }

            })

            ws?.connect()

        }catch (e:Exception) {
            Log.d("errorcito", e.toString())
        }finally {
            sendMessage()
        }

        getListOfAirtypes()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition: Int = viewHolder.adapterPosition
                val toPosition: Int = target.adapterPosition

                Collections.swap(INSTANCIA.getAirplaneTypes(), fromPosition, toPosition)

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
/*                        ruta = AirplaneType(INSTANCIA.getAirtypes()[position], INSTANCIA.getAirtypes()[position].lastName,
                            jobs.getApplications()[position].streetAddress1, jobs.getApplications()[position].streetAddress2, jobs.getApplications()[position].city, jobs.getApplications()[position].state, jobs.getApplications()[position].postal, jobs.getApplications()[position].country, jobs.getApplications()[position].email, jobs.getApplications()[position].areaCode, jobs.getApplications()[position].phone, jobs.getApplications()[position].position, jobs.getApplications()[position].date)
                        jobs.deleteJob(position)
                        lista.adapter?.notifyItemRemoved(position)

                        Snackbar.make(lista, aplication.firstName + "Se eliminaría... ", Snackbar.LENGTH_LONG).setAction("Undo") {
                            jobs.getApplications().add(position, aplication)
                            lista.adapter?.notifyItemInserted(position)
                        }.show()
                        adaptador = RecyclerView_Adapter(jobs.getApplications())
                        lista.adapter = adaptador
                        adaptador.notifyAirtypesSetChanged()*/
                }else{
                    /*val intent = Intent(this@CRUDJobs, EditAplication::class.java)
                    val item = jobs.getJob(position)
                    intent.putExtra("dato", item )
                    intent.putExtra("position",position)
                    startActivity(intent)
                    adaptador.notifyAirtypesSetChanged()
                    //getListOfPersons()*/
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                RecyclerViewSwipeDecorator.Builder(this@CRUDAirType, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(
                        ContextCompat.getColor(this@CRUDAirType,
                            R.color.Ruby_Red
                        ))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeRightBackgroundColor(
                        ContextCompat.getColor(this@CRUDAirType,
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

    private fun getListOfAirtypes() {
        val Nrutas = ArrayList<AirplaneType>()
        for (p in INSTANCIA.getAirplaneTypes()) {
            Nrutas.add(p)
        }
        adaptador = RecyclerView_Adapter_AirplaneType(Nrutas)
        lista.adapter = adaptador
    }

    fun sendMessage(){
        if(ws?.isOpen()==true){
            Log.d("boton","entra")
            val x = JSONObject()
            x.put("action","types-list")
            ws?.sendText(x.toString())
        }else{
            Log.d("boton","no entro")
        }
    }

}