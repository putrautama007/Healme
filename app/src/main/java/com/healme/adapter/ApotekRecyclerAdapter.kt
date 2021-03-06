package com.healme.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.healme.R
import com.healme.model.Apotek
import kotlinx.android.synthetic.main.item_apotek.view.*
import android.content.Intent
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.healme.ApotekLocationActivity
import com.healme.fragment.HomeFragment


class ApotekRecyclerAdapter(var context: Context, var apoteks: List<Apotek>, var layout: Int? = null) :
        RecyclerView.Adapter<ApotekRecyclerAdapter.ViewHoler>() {

    class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama = view.tv_nama_apotek
        val btnApotek = view.btn_apotek
        val foto = view.img_foto_apotek

        fun bintItem(apotek: Apotek) {
            tvNama.text = apotek.nama
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHoler {
        if (layout == null){
            return ViewHoler(LayoutInflater.from(context).inflate(R.layout.item_apotek_second, p0, false))
        }else{
            return ViewHoler(LayoutInflater.from(context).inflate(layout!!, p0, false))
        }
//        if (context == HomeFragment::class.java) {
//        } else {
//            return ViewHoler(LayoutInflater.from(context).inflate(R.layout.item_apotek_second, p0, false))
//        }
    }

    override fun getItemCount(): Int = apoteks.size

    override fun onBindViewHolder(p0: ViewHoler, p1: Int) {
        val apotek = apoteks[p1]

        p0.bintItem(apotek)
        Glide.with(context).load(apotek.foto).into(p0.foto)
        p0.btnApotek.setOnClickListener {
            val intent = Intent(context, ApotekLocationActivity::class.java)
            intent.putExtra("id", apotek.id)
            intent.putExtra("nama", apotek.nama)
            intent.putExtra("latitude", apotek.latlong?.latitude)
            intent.putExtra("longitude", apotek.latlong?.longitude)
            intent.putExtra("lokasi", apotek.lokasi)
            context.startActivity(intent)
        }
    }
}