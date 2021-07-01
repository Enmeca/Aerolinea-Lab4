package com.cabegaira.aerolinea.websockets

import android.util.Log
import com.cabegaira.aerolinea.data.Airtypes
import com.cabegaira.aerolinea.logic.AirplaneType
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class AirtypesController/*
                    "id":"Airbus 320","year":2000,"model":"320",
                    "brand":"Airbus","passengersQuantity":132,"rowsNumber":6,"columnsNumber":22}
                 */() {
    var ws: WebSocket? = null
    var factory : WebSocketFactory? =null
    var types: Airtypes = Airtypes.instance

    init {
        factory = WebSocketFactory().setConnectionTimeout(5000)
        try {
            ws = factory?.createSocket("ws://52.167.232.76:9393/controllertypes")

            ws?.addListener( object: WebSocketAdapter(){
                /*
                    "id":"Airbus 320","year":2000,"model":"320",
                    "brand":"Airbus","passengersQuantity":132,"rowsNumber":6,"columnsNumber":22}
                 */
                override fun onTextMessage(websocket: WebSocket?, text: String?) {
                    super.onTextMessage(websocket, text)
                    Log.d("TESTE","ontextmesage"+text)
                    val jsonArray = JSONTokener(text).nextValue() as JSONArray
                    types.clearList()

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
                        types.addAirplaneType(svtype)

                    }
                }

            })

            ws?.connectAsynchronously()

        }catch (e:Exception){
            Log.d("error", e.toString())
        }
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