package com.healme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_see_more.*

class SeeMoreActivity : AppCompatActivity(), View.OnClickListener  {
    override fun onClick(v: View?) {
        when(v){
            btn_back_see_more ->{
                onBackPressed()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_more)
        btn_back_see_more.setOnClickListener(this)
    }
}
