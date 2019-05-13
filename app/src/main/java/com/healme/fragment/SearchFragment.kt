package com.healme.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import com.healme.R
import com.healme.adapter.ApotekRecyclerAdapter
import com.healme.adapter.SeeMoreAdapter
import com.healme.helper.ApiClient
import com.healme.model.Apotek
import com.healme.model.Obat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var adapterApotek: ApotekRecyclerAdapter
    private lateinit var radioApotek: RadioButton
    private val listApotek: MutableList<Apotek> = mutableListOf()
    private lateinit var adapterObat: SeeMoreAdapter
    private lateinit var radioObat: RadioButton
    private val listObat: MutableList<Obat> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.rv_search)
        radioApotek = view.findViewById(R.id.rbtn_apotek)
        radioObat = view.findViewById(R.id.rbtn_obat)
        etSearch = view.findViewById(R.id.et_search)

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()) {
                    listApotek.clear()
                    listObat.clear()
                } else
                    getResult(s)
            }
        })
        return view
    }

    private fun getResult(text: CharSequence) {
        val response = ApiClient.create()
        if (radioApotek.isChecked)
            response.getApotek().enqueue(object : Callback<List<Apotek>> {
                override fun onFailure(call: Call<List<Apotek>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<Apotek>>, response: Response<List<Apotek>>) {
                    listApotek.clear()
                    response.body()?.forEach {
                        if (it.nama?.contains(text, true)!!)
                            listApotek.add(it)

                        adapterApotek = ApotekRecyclerAdapter(context!!, listApotek)
                        recyclerView.adapter = adapterApotek
                        recyclerView.layoutManager = LinearLayoutManager(context)
                    }
                }
            })
        else if (radioObat.isChecked)
            response.getObat().enqueue(object : Callback<List<Obat>> {
                override fun onFailure(call: Call<List<Obat>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<Obat>>, response: Response<List<Obat>>) {
                    listObat.clear()
                    response.body()?.forEach {
                        if (it.nama?.contains(text, true)!!)
                            listObat.add(it)

                        adapterObat = SeeMoreAdapter(context!!, listObat)
                        recyclerView.adapter = adapterObat
                        recyclerView.layoutManager = GridLayoutManager(context, 3)
                    }
                }
            })
    }
}
