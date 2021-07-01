package com.cabegaira.aerolinea

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cabegaira.aerolinea.data.Airtypes
import com.cabegaira.aerolinea.logic.Airplane
import com.cabegaira.aerolinea.logic.AirplaneType
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import org.json.JSONObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import org.json.JSONArray
import org.json.JSONTokener

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    var ws: WebSocket? = null
    var factory : WebSocketFactory? =null
    var gson:Gson = Gson()

    var types:Airtypes = Airtypes.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        factory = WebSocketFactory().setConnectionTimeout(5000)
        btn.setOnClickListener {
            Log.d("teste","idk")
            sendMessage()

        }
        try {
            ws = factory?.createSocket("ws://52.167.232.76:9393/controllerplanes")

            ws?.addListener( object: WebSocketAdapter(){

                override fun onTextMessage(websocket: WebSocket?, text: String?) {
                    super.onTextMessage(websocket, text)
                    Log.d("TESTE","ontextmesage"+text)
                    val jsonArray = JSONTokener(text).nextValue() as JSONArray
                    types.clearList()

                    val itemType = object : TypeToken<List<Airplane>>(){}.type
                    var result: ArrayList<Airplane> = gson.fromJson(text, itemType);

/*                    for (i in 0 until jsonArray.length()){
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

                    }*/
                    Log.d("LIST",result.toString())
                }

            })

            ws?.connectAsynchronously()

        }catch (e:Exception){
            Log.d("errorcito", e.toString())
        }

    }

    fun sendMessage(){
        if(ws?.isOpen()==true){
            Log.d("boton","entra")
            val x = JSONObject()
            x.put("action","planes-list")
            ws?.sendText(x.toString())
        }else{
            Log.d("boton","no entro")
        }
    }
}
