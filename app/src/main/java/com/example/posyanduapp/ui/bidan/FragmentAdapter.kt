package com.example.posyanduapp.ui.bidan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.posyanduapp.ui.bidan.GrafikFragments.BeratBadanUmurFragment
import com.example.posyanduapp.ui.bidan.GrafikFragments.LingkarKepalaUmurFragment
import com.example.posyanduapp.ui.bidan.GrafikFragments.TinggiBadanUmurFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return  when(position){
            0->{
                BeratBadanUmurFragment()
            }
            1->{
                TinggiBadanUmurFragment()
            }
            2->{
                LingkarKepalaUmurFragment()
            }
            else->{
                Fragment()
            }
        }
    }
}