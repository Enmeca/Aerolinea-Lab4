package com.cabegaira.aerolinea.CRUD.Tickets

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.cabegaira.aerolinea.CRUD.Flight.UserFlight
import com.cabegaira.aerolinea.Exitosa
import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Flight
import com.google.gson.reflect.TypeToken
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import kotlinx.android.synthetic.main.tickets_selection.*


class TicketsSelection : AppCompatActivity() {

    private var btn : Button? = null
    var letter  = listOf<String>("A","B","C","D","E","F",
                                 "G","H","I","J","K","L");

    var ws: WebSocket? = null
    var factory : WebSocketFactory? =null
    val policy = StrictMode.ThreadPolicy.Builder()
        .permitAll()
        .build()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tickets_selection);
        loadSeats(12,6)
        btn = findViewById(R.id.confirm) as Button
        btn!!.setOnClickListener{
            val i = Intent(this, Exitosa::class.java)
            Toast.makeText(this, "Compra Realizada", Toast.LENGTH_SHORT).show()
            startActivity(i)
            finish()
        }
        StrictMode.setThreadPolicy(policy)
        factory = WebSocketFactory().setConnectionTimeout(5000)

        try {
            ws = factory?.createSocket("ws://52.167.232.76:9393/controllerflights")
            ws?.connect()
        }catch (e:Exception) {
            Log.d("errorcito", e.toString())
        }finally {
            //sendMessage()
        }

    }

    val seats = mutableListOf<String>()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun loadSeats(rows: Int, cols: Int) {
        var seatslayout : LinearLayout = findViewById(R.id.layoutAsi)
        var vuelo : TextView = findViewById(R.id.tvRuta)
        var bundle = intent.extras
        var ruta : String? = bundle!!.getString("Ruta")
        var x : Int = 0
        var seat : String = ""
        var aux : String = ""
        vuelo.text = ""
        for (i in 1..rows) {


            x++

            val layout: LinearLayout = LinearLayout(applicationContext)
            layout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            layout.setHorizontalGravity(Gravity.CENTER)
            layout.orientation = LinearLayout.HORIZONTAL
            for (j in 1..cols) {
                val btn: Button = Button(applicationContext)

                if(j==4){
                    val btnP: Button = Button(applicationContext)
                    btnP.layoutParams = LinearLayout.LayoutParams(90, 110)
                    btnP.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
                    btnP.tag = i.toString() + j.toString()
                    layout.addView(btnP)
                }


                btn.layoutParams = LinearLayout.LayoutParams(130, 130)
                btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#808080"))
                btn.tag = i.toString() + j.toString()


                btn!!.text =letter[i-1] +j.toString();


                layout.addView(btn)
                btn.setOnClickListener {
                    if (btn.backgroundTintList!!.defaultColor == -15132207) {
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#808080"))
                        seats.remove(btn.tag.toString())
                    }
                    else if(seats.count() < 5) {
                        seats.add(btn.tag.toString());
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#158dce"))
                    }
                }
            }
            seatslayout.addView(layout)
        }
    }

    fun setReservation(){



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