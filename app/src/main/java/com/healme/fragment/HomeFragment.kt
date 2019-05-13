package com.healme.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.healme.R
import com.healme.SeeMoreActivity
import com.healme.adapter.ApotekRecyclerAdapter
import com.healme.adapter.SeeMoreAdapter
import com.healme.helper.ApiClient
import com.healme.model.Apotek
import com.healme.model.Obat
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvObat: RecyclerView
    private lateinit var adapter: ApotekRecyclerAdapter
    private val listApotek: MutableList<Apotek> = mutableListOf()
    private lateinit var btnBatuk: ImageButton
    private lateinit var btnFlu: ImageButton
    private lateinit var btnSakitKepala: ImageButton
    private lateinit var btnSeeAll: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.rv_apotek)
        rvObat = view.findViewById(R.id.rv_obat)
        adapter = ApotekRecyclerAdapter(context!!, listApotek, R.layout.item_apotek)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        btnBatuk = view.findViewById(R.id.btn_cough)
        btnFlu = view.findViewById(R.id.btn_sneeze)
        btnSakitKepala = view.findViewById(R.id.btn_headache)
        btnSeeAll = view.findViewById(R.id.btn_see_all)
        btnBatuk.setOnClickListener {
            val intent = Intent(context, SeeMoreActivity::class.java)
            intent.putExtra("nama", tv_cough.text)
            intent.putExtra("kategori", tv_cough.text)
            startActivity(intent)
        }
        btnFlu.setOnClickListener {
            val intent = Intent(context, SeeMoreActivity::class.java)
            intent.putExtra("nama", tv_sneeze.text)
            intent.putExtra("kategori", tv_sneeze.text)
            startActivity(intent)
        }
        btnSakitKepala.setOnClickListener {
            val intent = Intent(context, SeeMoreActivity::class.java)
            intent.putExtra("nama", tv_headache.text)
            intent.putExtra("kategori", tv_headache.text)
            startActivity(intent)
        }
        btnSeeAll.setOnClickListener {
            val intent = Intent(context, SeeMoreActivity::class.java)
            intent.putExtra("nama", tv_see_all.text)
            intent.putExtra("kategori", tv_see_all.text)
            startActivity(intent)
        }

        getListApotek()
        getListObat()
        return view
    }

    private fun getListApotek() {
        val apotekResponse = ApiClient.create()
        apotekResponse.getApotek().enqueue(object : Callback<List<Apotek>> {
            override fun onFailure(call: Call<List<Apotek>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Apotek>>, response: Response<List<Apotek>>) {
                response.body()?.forEach {
                    listApotek.add(it)
                    Log.d("data foto", "${it.foto}")
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun getListObat(){
        val obatResponse = ApiClient.create()
        val obatList: MutableList<Obat> = mutableListOf()
        obatResponse.getObat().enqueue(object : Callback<List<Obat>>{
            override fun onFailure(call: Call<List<Obat>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Obat>>, response: Response<List<Obat>>) {
                obatList.addAll(response.body()!!)
                val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                val adapter = context?.let { SeeMoreAdapter(it,obatList) }
                rvObat.layoutManager = layoutManager
                rvObat.adapter = adapter
                adapter?.notifyDataSetChanged()
            }
        })
    }
}
