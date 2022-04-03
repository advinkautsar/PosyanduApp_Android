package com.example.posyanduapp.ui.orangtua.GrafikFragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.posyanduapp.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_berat_badan_umur.*


class BeratBadanUmurFragment : Fragment() {

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
    ): View {
        // Inflate the layout for this fragment
         return inflater.inflate(R.layout.fragment_berat_badan_umur, container, false)
        val view: View = inflater!!.inflate(R.layout.fragment_berat_badan_umur, container, false)
        // Return the fragment view/layout

        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lineChart = view.findViewById(R.id.line_chart_bbu)

        linelist = ArrayList()
        linelist.add(Entry(10f,100f))
        linelist.add(Entry(20f,300f))
        linelist.add(Entry(30f,250f))
        linelist.add(Entry(40f,700f))
        linelist.add(Entry(50f,330f))
        linelist.add(Entry(60f,210f))
        linelist.add(Entry(70f,150f))

        lineDataSet = LineDataSet(linelist,"Berat Badan Anak")
        lineData= LineData(lineDataSet)
        lineChart.data=lineData
        lineDataSet.color = Color.GREEN
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize= 13f
        lineDataSet.setDrawFilled(true)







    }


}