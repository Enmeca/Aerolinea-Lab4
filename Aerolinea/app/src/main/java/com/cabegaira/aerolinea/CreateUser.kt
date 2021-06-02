package com.cabegaira.aerolinea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cabegaira.aerolinea.data.Users
import com.cabegaira.aerolinea.logic.User

class CreateUser : AppCompatActivity() {

    var users: Users = Users.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_user_activity)

        var et_name = findViewById(R.id.et_name) as EditText
        var et_user = findViewById(R.id.et_user) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_create = findViewById(R.id.btn_create) as Button

        btn_create.setOnClickListener{
            var name = et_name.text;
            val password = et_password.text
            val user = et_user.text
            users.addUser(User(user.toString().lowercase(), password.toString(), name.toString(),0))
            Toast.makeText(this, "El usuario se ha registrado", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Login::class.java)
            intent.putExtra("MESSAGE", "msg")
            startActivity(intent)
        }

    }
}