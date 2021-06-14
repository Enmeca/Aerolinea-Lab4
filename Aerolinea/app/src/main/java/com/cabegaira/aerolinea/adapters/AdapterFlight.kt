package com.cabegaira.aerolinea.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cabegaira.aerolinea.CRUD.Flight.FlightDetail
import com.cabegaira.aerolinea.CRUD.Tickets.TicketsSelection
import com.cabegaira.aerolinea.Login

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.data.Flights
import com.cabegaira.aerolinea.logic.Flight

import kotlin.collections.ArrayList

class RecyclerView_Adapter_Flight(private var items: ArrayList<Flight>): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Flight>? = null

    lateinit var mcontext: Context

    class FlightHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val FlightListView = LayoutInflater.from(parent.context).inflate(R.layout.template_flight, parent, false)
        val sch = FlightHolder(FlightListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)
        holder.itemView.findViewById<TextView>(R.id.tvNombre)?.text = item?.ruta
        holder.itemView.findViewById<TextView>(R.id.tvPrecio)?.text = item?.price.toString()
        holder.itemView.findViewById<ImageView>(R.id.ivFoto).setImageResource(item?.Foto!!)
        holder.itemView.findViewById<ImageView>(R.id.ivPrice).setImageResource(R.drawable.dollar)
        //holder.itemView.findViewById<ImageView>(R.id.ivFoto).setImageResource(item?.Foto!!)
        holder.itemView.setOnClickListener {
            val intent = Intent(this.mcontext, FlightDetail::class.java)
            intent.putExtra("dato", item)
            intent.putExtra("position",position)
            this.mcontext.startActivity(intent)
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Flight>()
                    for (row in items) {
                        if (row.ruta.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    itemsList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemsList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemsList = results?.values as ArrayList<Flight>
                notifyDataSetChanged()
            }

        }
    }
}