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

class LingkarKepalaUmurFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_lingkar_kepala_umur, container, false)

        val view: View = inflater!!.inflate(R.layout.fragment_lingkar_kepala_umur, container, false)



        // Return the fragment view/layout
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lineChart = view.findViewById(R.id.line_chart_lku)

        linelist = ArrayList()
        linelist.add(Entry(1f,5f))
        linelist.add(Entry(2f,6f,10))
        linelist.add(Entry(3f,8f))
        linelist.add(Entry(4f,10f))
        linelist.add(Entry(5f,13f))
        linelist.add(Entry(6f,16f))
        linelist.add(Entry(7f,18f))

        lineDataSet = LineDataSet(linelist,"Lingkar Kepala Anak")
        lineData= LineData(lineDataSet)
        lineChart.data=lineData
        lineDataSet.color = Color.BLACK
        lineDataSet.valueTextColor = Color.MAGENTA
        lineDataSet.valueTextSize= 13f
        lineDataSet.setDrawFilled(true)







    }

}