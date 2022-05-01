package com.bizmiz.bookieuz.ui.main.genre.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bizmiz.bookieuz.ui.main.my_book.favourite_books.FavouriteBookFragment
import com.bizmiz.bookieuz.ui.main.my_book.play_books.PlayMyBooksFragment

class MyBookPagerAdapter(
    fragmentActivity: FragmentActivity,
    var fragmentList: MutableList<Fragment>
) :
    FragmentStateAdapter(fragmentActivity) {

    private val TAG by lazy { this::class.java.simpleName }

    override fun createFragment(position: Int): Fragment {

        Log.i(TAG, "createFragment position = $position")

        val fragment: Fragment = when (position) {
            0 -> PlayMyBooksFragment()
            else -> FavouriteBookFragment()
        }

        if (fragmentList.size <= position)
            fragmentList.add(fragment)
        else
            fragmentList[position] = fragment

        Log.i(TAG, "fra: fragmentList.size = ${fragmentList.size}")

        return fragment
    }


    override fun getItemCount(): Int {
        Log.i(TAG, "fra: fragmentListSize = ${fragmentList.size}")
        return 2
    }

}