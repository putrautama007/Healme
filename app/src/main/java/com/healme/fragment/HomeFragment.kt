package com.healme.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.healme.R
import com.healme.adapter.ApotekRecyclerAdapter
import com.healme.helper.ApiClient
import com.healme.model.Apotek
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ApotekRecyclerAdapter
    private val listApotek: MutableList<Apotek> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.rv_apotek)
        getListApotek()
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
                    Log.d("data foto","${it.foto}")
                }
                adapter = ApotekRecyclerAdapter(context!!.applicationContext, listApotek)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }

        })
    }
}
