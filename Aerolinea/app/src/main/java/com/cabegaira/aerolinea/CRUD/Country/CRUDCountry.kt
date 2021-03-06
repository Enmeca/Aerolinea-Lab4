package com.cabegaira.aerolinea.CRUD.Country

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*
import kotlin.collections.ArrayList

import com.cabegaira.aerolinea.adapters.RecyclerView_Adapter_Country


import com.cabegaira.aerolinea.Login
import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Country
import com.cabegaira.aerolinea.data.Countries

class CRUDCountry : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


        var INSTANCIA: Countries = Countries.instance

        lateinit var lista: RecyclerView
        lateinit var adaptador: RecyclerView_Adapter_Country
        lateinit var ruta: Country
        var archived = ArrayList<Country>()
        var position: Int = 0



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(R.layout.list_countries_activity)

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

            findViewById<SearchView>(R.id.country_search).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adaptador.filter.filter(newText)
                    return false
                }
            })

            getListOfCountries()

            val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    val fromPosition: Int = viewHolder.adapterPosition
                    val toPosition: Int = target.adapterPosition

                    Collections.swap(INSTANCIA.getCountries(), fromPosition, toPosition)

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
/*                        ruta = Country(INSTANCIA.getCountries()[position], INSTANCIA.getCountries()[position].lastName,
                            jobs.getApplications()[position].streetAddress1, jobs.getApplications()[position].streetAddress2, jobs.getApplications()[position].city, jobs.getApplications()[position].state, jobs.getApplications()[position].postal, jobs.getApplications()[position].country, jobs.getApplications()[position].email, jobs.getApplications()[position].areaCode, jobs.getApplications()[position].phone, jobs.getApplications()[position].position, jobs.getApplications()[position].date)
                        jobs.deleteJob(position)
                        lista.adapter?.notifyItemRemoved(position)

                        Snackbar.make(lista, aplication.firstName + "Se eliminar??a... ", Snackbar.LENGTH_LONG).setAction("Undo") {
                            jobs.getApplications().add(position, aplication)
                            lista.adapter?.notifyItemInserted(position)
                        }.show()
                        adaptador = RecyclerView_Adapter(jobs.getApplications())
                        lista.adapter = adaptador
                        adaptador.notifyCountriesSetChanged()*/
                    }else{
                        /*val intent = Intent(this@CRUDJobs, EditAplication::class.java)
                        val item = jobs.getJob(position)
                        intent.putExtra("dato", item )
                        intent.putExtra("position",position)
                        startActivity(intent)
                        adaptador.notifyCountriesSetChanged()
                        //getListOfPersons()*/
                    }
                }

                override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                    RecyclerViewSwipeDecorator.Builder(this@CRUDCountry, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(
                            ContextCompat.getColor(this@CRUDCountry,
                            R.color.Ruby_Red
                        ))
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                        .addSwipeRightBackgroundColor(
                            ContextCompat.getColor(this@CRUDCountry,
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

        private fun getListOfCountries() {
            val Nrutas = ArrayList<Country>()
            for (p in INSTANCIA.getCountries()) {
                Nrutas.add(p)
            }
            adaptador = RecyclerView_Adapter_Country(Nrutas)
            lista.adapter = adaptador
        }

    }