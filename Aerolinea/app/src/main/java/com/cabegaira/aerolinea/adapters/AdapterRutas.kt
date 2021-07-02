package com.cabegaira.aerolinea.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Route

import kotlin.collections.ArrayList

class RecyclerView_Adapter_Routes(private var items: ArrayList<Route>): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Route>? = null

    lateinit var mcontext: Context

    class PersonHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val personListView = LayoutInflater.from(parent.context).inflate(R.layout.template_routes, parent, false)
        val sch = PersonHolder(personListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)
        holder.itemView.findViewById<TextView>(R.id.tvNombre)?.text = item?.destination
        holder.itemView.findViewById<TextView>(R.id.tvFecha)?.text = "no tiene"
        //holder.itemView.findViewById<ImageView>(R.id.ivFoto).setImageResource(item?.Foto!!)

        /*holder.itemView.setOnClickListener {
            val intent = Intent(this.mcontext, EditAplication::class.java)
            intent.putExtra("dato", item)
            intent.putExtra("position",position)
            this.mcontext.startActivity(intent)
        }*/
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    /*val resultList = ArrayList<Routes>()
                    for (row in items) {
                        if (row.firstName.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                        if (row.lastName.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                        if (row.position.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                        if (row.city.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                        if (row.phone.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    itemsList = resultList*/
                }
                val filterResults = FilterResults()
                filterResults.values = itemsList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemsList = results?.values as ArrayList<Route>
                notifyDataSetChanged()
            }

        }
    }
}