package com.healme

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> view_pager.currentItem = 0
                R.id.search -> view_pager.currentItem = 1
                R.id.scan -> view_pager.currentItem = 2
            }
            true
        }

        bottom_nav.selectedItemId = R.id.home

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null)
                    prevMenuItem?.isChecked = false
                else
                    bottom_nav.menu.getItem(position).isChecked = true
                bottom_nav.menu.getItem(position).isChecked = true
                prevMenuItem = bottom_nav.menu.getItem(position)
            }
        })

        view_pager.adapter = MainFragmentAdapter(supportFragmentManager)
    }
}
