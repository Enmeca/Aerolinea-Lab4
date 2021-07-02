package com.cabegaira.aerolinea

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.cabegaira.aerolinea.CRUD.Airplane.CRUDAirplane
import com.cabegaira.aerolinea.CRUD.AirplaneType.CRUDAirType
import com.cabegaira.aerolinea.CRUD.Flight.CRUDFlight
import com.cabegaira.aerolinea.CRUD.Flight.UserFlight
import com.cabegaira.aerolinea.CRUD.Reservation.CRUDReservation
import com.cabegaira.aerolinea.CRUD.Rutas.CRUDRutas
import com.cabegaira.aerolinea.CRUD.Rutas.SearchRoute
import com.cabegaira.aerolinea.logic.User
import com.google.android.material.navigation.NavigationView


class MainMenu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu_activity)

        val bundle = intent.extras
        val msg = bundle!!.getString("msg")
        val l =  bundle.getSerializable("Login") as User



        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_vuelos -> {
                Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show()
                val i = Intent(this, UserFlight::class.java)
                startActivity(i)
            }
            R.id.nav_viajes -> {
                Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show()
                val i = Intent(this, CRUDReservation::class.java)
                startActivity(i)
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show()
                val i = Intent(this, Login::class.java)
                startActivity(i)
                finish()
            }
        }
        return true
    }
}
