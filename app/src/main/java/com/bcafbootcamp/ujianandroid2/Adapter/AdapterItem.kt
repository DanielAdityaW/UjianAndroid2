package com.bcafbootcamp.ujianandroid2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcafbootcamp.ujianandroid2.Model.ModelItem
import com.bcafbootcamp.ujianandroid2.R

class AdapterItem(private var listItem: List<ModelItem>,
                  private val onClick: (ModelItem) -> Unit,
                  private val onLongClick: (ModelItem) -> Unit) :
    RecyclerView.Adapter<AdapterItem.ItemViewHolder>() {

    private var fullList: List<ModelItem> = listItem

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNama = itemView.findViewById<TextView>(R.id.txtNama)
        val txtOst = itemView.findViewById<TextView>(R.id.txtOutstanding)
        val txtAlamat = itemView.findViewById<TextView>(R.id.txtAlamat)
        val layoutParent = itemView.findViewById<LinearLayout>(R.id.parentItem)

        init {
            layoutParent.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClick(listItem[position])
                }
            }

            layoutParent.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onLongClick(listItem[position])
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listItem[position]
        holder.txtNama.text = item.nama
        holder.txtOst.text = item.outstanding
        holder.txtAlamat.text = item.alamat
    }

    fun filterList(filteredItems: List<ModelItem>) {
        listItem = filteredItems
        notifyDataSetChanged()
    }
}
