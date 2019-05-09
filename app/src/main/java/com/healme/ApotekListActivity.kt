package com.healme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.healme.adapter.ApotekRecyclerAdapter
import com.healme.model.Apotek
import kotlinx.android.synthetic.main.activity_apotek_list.*

class ApotekListActivity : AppCompatActivity() {
    private lateinit var adapter: ApotekRecyclerAdapter
    private var apoteks: MutableList<Apotek> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apotek_list)

        val layoutManager = LinearLayoutManager(this)
        adapter = ApotekRecyclerAdapter(this,apoteks)
        apotek_recyclerView.layoutManager = layoutManager
        apotek_recyclerView.adapter = adapter

        apoteks.add(Apotek("1","Apotek 24"))
        apoteks.add(Apotek("2","Apotek K"))
        adapter.notifyDataSetChanged()
    }

}
