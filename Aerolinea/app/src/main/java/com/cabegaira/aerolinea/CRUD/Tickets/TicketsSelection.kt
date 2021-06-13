package com.cabegaira.aerolinea.CRUD.Tickets

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.cabegaira.aerolinea.R


class TicketsSelection : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tickets_selection);
        cargarAsientos(8,8);
    }

    val asientos = mutableListOf<String>()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun cargarAsientos(filas: Int, columnas: Int) {
        var seatslayout : LinearLayout = findViewById(R.id.layoutAsi)
        var cant : TextView = findViewById(R.id.tvCantidad)
        //var bundle = intent.extras
        //var cantidadA : String = bundle!!.getInt("cantidadAsientos").toString()

        //cant.text = "Asientos a elegir: "+ cantidadA
        for (i in 1..filas) {
            val layout: LinearLayout = LinearLayout(applicationContext)
            layout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layout.setHorizontalGravity(Gravity.CENTER)
            layout.orientation = LinearLayout.HORIZONTAL
            for (j in 1..columnas) {
                val btn: Button = Button(applicationContext)
                btn.layoutParams = LinearLayout.LayoutParams(110, 110)
                btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3268F3"))
                btn.tag = i.toString() + j.toString()
                layout.addView(btn)
                btn.setOnClickListener {
                    if (btn.backgroundTintList!!.defaultColor == -15348162) {
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3268F3"))
                        asientos.remove(btn.tag.toString())
                    } else if(asientos.count() < 20) {
                        asientos.add(btn.tag.toString());
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#15CE3E"))
                    }
                }
            }
            seatslayout.addView(layout)
        }
    }
}