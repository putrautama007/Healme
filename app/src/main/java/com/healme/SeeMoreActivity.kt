package com.healme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.healme.adapter.SeeMoreAdapter
import com.healme.helper.ApiClient
import com.healme.model.Apotek
import com.healme.model.Obat
import kotlinx.android.synthetic.main.activity_see_more.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeeMoreActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SeeMoreAdapter

    override fun onClick(v: View?) {
        when (v) {
            btn_back_see_more -> {
                onBackPressed()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_more)
        btn_back_see_more.setOnClickListener(this)
        nama_apotek.text = intent.getStringExtra("nama")
        recyclerView = findViewById(R.id.rv_list_obat)
        if(intent.hasExtra("id"))
            loadDataObat(intent.getIntExtra("id", 0))
        else if (intent.hasExtra("kategori"))
            loadDataObat(intent.getStringExtra("kategori"))
        Log.d("id", "${intent.getIntExtra("id", 0)}")
        Log.d("id", "${intent.getStringExtra("nama")}")
    }

    private fun loadDataObat(id: Int) {
        val apotekResponse = ApiClient.create().getApotek()
        var obatList: List<Obat>
        apotekResponse.enqueue(object : Callback<List<Apotek>> {
            override fun onFailure(call: Call<List<Apotek>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Apotek>>, response: Response<List<Apotek>>) {
                response.body()?.forEach {
                    if (it.id == id) {
                        obatList = it.obat!!
                        adapter = SeeMoreAdapter(this@SeeMoreActivity, obatList)
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = GridLayoutManager(this@SeeMoreActivity, 2, LinearLayoutManager.VERTICAL, false)
                    }
                }

            }
        })
    }

    private fun loadDataObat(kategori: String) {
        val obatResponse = ApiClient.create().getObat()
        val obatList: MutableList<Obat> = mutableListOf()
        obatResponse.enqueue(object : Callback<List<Obat>> {
            override fun onFailure(call: Call<List<Obat>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Obat>>, response: Response<List<Obat>>) {
                if (kategori.equals("See All"))
                    response.body()?.forEach {
                        obatList.add(it)
                        adapter = SeeMoreAdapter(this@SeeMoreActivity, obatList)
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = GridLayoutManager(this@SeeMoreActivity, 2)
                    }
                else
                    response.body()?.forEach {
                        if (it.kategori!!.contains(kategori)) {
                            obatList.add(it)
                            adapter = SeeMoreAdapter(this@SeeMoreActivity, obatList)
                            recyclerView.adapter = adapter
                            recyclerView.layoutManager = GridLayoutManager(this@SeeMoreActivity, 2)
                        }
                    }
            }
        })
    }
}
