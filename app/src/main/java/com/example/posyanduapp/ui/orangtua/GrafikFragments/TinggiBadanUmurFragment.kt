package com.example.posyanduapp.ui.orangtua.GrafikFragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.orangtua.StatusGiziActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class TinggiBadanUmurFragment : Fragment() {

    lateinit var lineChart: LineChart
    lateinit var lineDataSet: LineDataSet
    lateinit var lineData: LineData
    lateinit var linelist: ArrayList<Entry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tinggi_badan_umur, container, false)
        val view: View = inflater!!.inflate(R.layout.fragment_tinggi_badan_umur, container, false)



        // Return the fragment view/layout
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lineChart = view.findViewById(R.id.line_chart_tbu)

        linelist = ArrayList()
        linelist.add(Entry(10f,15f))
        linelist.add(Entry(20f,18f))
        linelist.add(Entry(30f,25f))
        linelist.add(Entry(40f,40f))
        linelist.add(Entry(50f,80f))
        linelist.add(Entry(60f,90f))
        linelist.add(Entry(70f,110f))

        lineDataSet = LineDataSet(linelist,"Tinggi Badan Anak")
        lineData= LineData(lineDataSet)
        lineChart.data=lineData
        lineDataSet.color = Color.BLACK
        lineDataSet.valueTextColor = Color.CYAN
        lineDataSet.valueTextSize= 13f
        lineDataSet.setDrawFilled(true)







    }

}