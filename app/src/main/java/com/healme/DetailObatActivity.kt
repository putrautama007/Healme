package com.healme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.healme.model.Obat
import kotlinx.android.synthetic.main.activity_detail_obat.*

class DetailObatActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v){
            btn_back_obat_details ->{
                onBackPressed()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_obat)
        btn_back_obat_details.setOnClickListener(this)
        val obatDetail = Gson().fromJson<Obat>(intent.getStringExtra("obat"),Obat::class.java)
        nama_obat.text = obatDetail.nama
        harga_obat.text = obatDetail.harga
        Glide.with(this).load(obatDetail.foto).into(iv_obat_detail)
        tv_isi_detail.text = obatDetail.detail
    }
}
