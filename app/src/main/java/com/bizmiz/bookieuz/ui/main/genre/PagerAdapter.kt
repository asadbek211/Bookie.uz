package com.bizmiz.bookieuz.ui.main.genre

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bizmiz.bookieuz.ui.main.genre.karakalpak_folklor.KarakalpakFolkloreFragment
import com.bizmiz.bookieuz.ui.main.genre.karakalpak_literature.KarakalpakLiteratureFragment
import com.bizmiz.bookieuz.ui.main.genre.short_audios.ShortAudiosFragment
import com.bizmiz.bookieuz.ui.main.genre.uzbek_literature.UzbekLiteratureFragment
import com.bizmiz.bookieuz.ui.main.genre.world_literature.WorldLiteratureFragment

class AddStationFragmentStateAdapter(
    fragmentActivity: FragmentActivity,
    var fragmentList: MutableList<Fragment>
) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment = when (position) {
            0 -> WorldLiteratureFragment()
            1 -> UzbekLiteratureFragment()
            2 -> KarakalpakLiteratureFragment()
            3 -> ShortAudiosFragment()
            else -> KarakalpakFolkloreFragment()
        }
        if (fragmentList.size <= position)
            fragmentList.add(fragment)
        else
            fragmentList[position] = fragment
        return fragment
    }
    override fun getItemCount(): Int {
        return 5
    }

}