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
import com.cabegaira.aerolinea.CRUD.Reservation.ReservationDetail
import com.cabegaira.aerolinea.Login

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Reservation
import java.util.*

import kotlin.collections.ArrayList

class RecyclerView_Adapter_Reservation(private var items: ArrayList<Reservation>): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Reservation>? = null

    lateinit var mcontext: Context

    class ReservationHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val ReservationListView = LayoutInflater.from(parent.context).inflate(R.layout.template_reservation, parent, false)
        val sch = ReservationHolder(ReservationListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)
        holder.itemView.findViewById<TextView>(R.id.tvRuta)?.text = item?.route?.destination
        //holder.itemView.findViewById<TextView>(R.id.tvFecha)?.text = item?.date
        holder.itemView.findViewById<TextView>(R.id.tvTickets)?.text = item?.seatQuantity.toString()
        holder.itemView.findViewById<ImageView>(R.id.ivTicket).setImageResource(R.drawable.ticket_icon)
        holder.itemView.findViewById<ImageView>(R.id.ivFoto).setImageResource(R.drawable.plane)

        holder.itemView.setOnClickListener {
            val intent = Intent(this.mcontext, ReservationDetail::class.java)
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
                    val resultList = ArrayList<Reservation>()
                    for (row in items) {
                        if (row.route?.destination!!.lowercase(Locale.getDefault()).contains(charSearch.lowercase(
                                Locale.getDefault()
                            ))) {
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
                itemsList = results?.values as ArrayList<Reservation>
                notifyDataSetChanged()
            }

        }
    }
}