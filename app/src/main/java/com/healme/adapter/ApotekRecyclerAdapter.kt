package com.healme.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.healme.R
import com.healme.model.Apotek
import kotlinx.android.synthetic.main.item_apotek.view.*

class ApotekRecyclerAdapter(var context: Context, var apoteks: List<Apotek>): RecyclerView.Adapter<ApotekRecyclerAdapter.ViewHoler>() {

    class ViewHoler(view: View): RecyclerView.ViewHolder(view) {
        val btnNama = view.itemApotek_namaBtn

        fun bintItem(apotek: Apotek){
            btnNama.setText(apotek.nama)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ApotekRecyclerAdapter.ViewHoler {
        return ViewHoler(LayoutInflater.from(context).inflate(R.layout.item_apotek,p0,false))
    }

    override fun getItemCount(): Int = apoteks.size

    override fun onBindViewHolder(p0: ApotekRecyclerAdapter.ViewHoler, p1: Int) {
        val apotek = apoteks.get(p1)

        p0.bintItem(apotek)
    }
}