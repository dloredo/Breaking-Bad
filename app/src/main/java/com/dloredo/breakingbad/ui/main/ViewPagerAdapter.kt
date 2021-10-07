package com.dloredo.breakingbad.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CharactersFragment.newInstance()
            1 -> PhraseFragment.newInstance()
            else -> SettingsFragment.newInstance()
        }
    }
}