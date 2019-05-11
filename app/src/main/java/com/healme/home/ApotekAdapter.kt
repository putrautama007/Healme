package com.healme.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.healme.R
import com.healme.model.Apotek
import kotlinx.android.synthetic.main.item_apotek.view.*

class ApotekAdapter(private val context: Context, private val listApotek: List<Apotek>): RecyclerView.Adapter<ApotekViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApotekViewHolder = ApotekViewHolder(LayoutInflater.from(context).inflate(R.layout.item_apotek, parent, false),context)

    override fun getItemCount(): Int = listApotek.size

    override fun onBindViewHolder(holder: ApotekViewHolder, position: Int) {
        holder.bindItem(listApotek[position])
    }

}

class ApotekViewHolder(view: View, private val context: Context): RecyclerView.ViewHolder(view) {
    fun bindItem(apotek: Apotek){
//        itemView.img_foto_apotek.setImageResource(apotek.foto)
        itemView.tv_nama_apotek.text = apotek.nama
    }
}
