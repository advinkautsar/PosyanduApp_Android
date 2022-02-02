package com.example.posyanduapp.ui.orangtua.GrafikFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.orangtua.StatusGiziActivity

class LingkarKepalaUmurFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lingkar_kepala_umur, container, false)

        val view: View = inflater!!.inflate(R.layout.fragment_lingkar_kepala_umur, container, false)



        // Return the fragment view/layout
        return view
    }

}