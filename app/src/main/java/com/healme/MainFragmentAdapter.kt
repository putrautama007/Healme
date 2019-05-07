package com.healme

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.healme.HomeFragment
import com.healme.ScanFragment
import com.healme.SearchFragment

class MainFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val tabTitle = arrayOf("Home", "Search", "Scan")

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = SearchFragment()
            2 -> fragment = ScanFragment()
        }

        return fragment
    }

    override fun getCount(): Int = tabTitle.size

    override fun getPageTitle(position: Int): CharSequence? = tabTitle[position]
}