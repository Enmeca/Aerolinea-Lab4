package com.cabegaira.aerolinea.websockets

import android.util.Log
import com.cabegaira.aerolinea.data.Airtypes
import com.cabegaira.aerolinea.logic.AirplaneType
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

object Teste {
    var ws: WebSocket? = null
    var factory : WebSocketFactory? =null
    var types: Airtypes = Airtypes.instance


    fun getData(){
        try {
            ws = factory?.createSocket("ws://52.167.232.76:9393/controllertypes")

            ws?.addListener( object: WebSocketAdapter(){
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
            Log.d("errorcito", e.toString())
        }finally {
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
}