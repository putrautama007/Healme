package com.healme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.healme.adapter.ApotekRecyclerAdapter
import com.healme.helper.ApiClient
import com.healme.helper.ApiInterface
import com.healme.model.Apotek
import kotlinx.android.synthetic.main.activity_apotek_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApotekListActivity : AppCompatActivity() {
    private lateinit var adapter: ApotekRecyclerAdapter
    private var apoteks: MutableList<Apotek> = mutableListOf()
    private lateinit var mApi: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apotek_list)

        val kodeObat = intent.getStringExtra("kode_obat")

        mApi = ApiClient.create()
        val layoutManager = LinearLayoutManager(this)
        adapter = ApotekRecyclerAdapter(this,apoteks)
        apotek_recyclerView.layoutManager = layoutManager
        apotek_recyclerView.adapter = adapter

        getListApotek(kodeObat.toLong())

    }

    fun getListApotek(kodeObat: Long){
        val apotekResponse = mApi.getApotek()
        apotekResponse.enqueue(object : Callback<List<Apotek>>{
            override fun onFailure(call: Call<List<Apotek>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Apotek>>, response: Response<List<Apotek>>) {
                response.body()?.forEach {
                    var apotek = it
                    it.obat?.forEach {
                        if (it.barcode == kodeObat){
                            apoteks.add(apotek)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }
}
