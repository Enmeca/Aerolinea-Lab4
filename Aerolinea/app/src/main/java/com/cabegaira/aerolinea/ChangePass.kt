package com.cabegaira.aerolinea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ChangePass : AppCompatActivity() {

    var users: Users = Users.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_pass_activity)

        var et_user = findViewById(R.id.et_user) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_change = findViewById(R.id.btn_change) as Button

        btn_change.setOnClickListener{
            val password = et_password.text
            val user = et_user.text
            users.changePass(user.toString(),password.toString())
            Toast.makeText(this, "Se ha cambiado la contraseña", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Login::class.java)
            intent.putExtra("MESSAGE", "msg")
            startActivity(intent)
        }
    }
}