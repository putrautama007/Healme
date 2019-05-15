package com.healme.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.healme.DetailObatActivity
import com.healme.R
import com.healme.model.Obat
import kotlinx.android.synthetic.main.row_see_more.view.*

class SeeMoreAdapter(val context: Context, val obat: List<Obat>) : RecyclerView.Adapter<SeeMoreAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_see_more, p0, false))
    }

    override fun getItemCount(): Int {
       return obat.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
       val obat = obat[p1]
        p0.namaObat.text = obat.nama
        p0.hargaObat.text = obat.harga
        Glide.with(context).load(obat.foto).into(p0.fotoObat)
        val detailObat = Gson().toJson(obat)
        p0.cvObat.setOnClickListener {
            val intent = Intent(context, DetailObatActivity::class.java)
            intent.putExtra("obat",detailObat)
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val namaObat = itemview.tv_nama_obat
        val fotoObat = itemview.iv_obat
        val cvObat = itemview.cv_obat
        val hargaObat = itemview.tv_harga_obat
    }
}