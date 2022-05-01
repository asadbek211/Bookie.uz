package com.bizmiz.bookieuz.ui.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bizmiz.bookieuz.ui.main.home.latest.LatestFragment
import com.bizmiz.bookieuz.ui.main.home.views.ViewsFragment

class HomePagerAdapter(
    fragmentActivity: FragmentActivity,
    var fragmentList: MutableList<Fragment>
) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment = when (position) {
            0 -> LatestFragment()
            else -> ViewsFragment()
        }
        if (fragmentList.size <= position)
            fragmentList.add(fragment)
        else
            fragmentList[position] = fragment
        return fragment
    }


    override fun getItemCount(): Int {
        return 2
    }

}