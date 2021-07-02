package com.cabegaira.aerolinea

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cabegaira.aerolinea.CRUD.Flight.UserFlight
import kotlinx.android.synthetic.main.exito.*
class Exitosa : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exito);

        button.setOnClickListener {
//            val i = Intent(this, UserFlight::class.java)
            Toast.makeText(this, "Compra Realizada", Toast.LENGTH_SHORT).show()
//            startActivity(i)
            finish()
        }
    }
}