package com.dragonguard.chart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class MainActivity : AppCompatActivity() {
    private lateinit var chart: BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chart = findViewById(R.id.barchart)
        var values1 = ArrayList<BarEntry>()
        var values2 = ArrayList<BarEntry>()

        for( i in 0 until 10){
            val value1 = (Math.random()*10).toFloat()
            val value2 = (Math.random()*10).toFloat()
            values1.add(BarEntry(i.toFloat(), value1))
            values2.add(BarEntry(i.toFloat(), value2))
        }
        val set1  = BarDataSet(values1, "DataSet 1")
        val set2  = BarDataSet(values2, "DataSet 2")

        val dataSets1: ArrayList<IBarDataSet> = ArrayList()
        dataSets1.add(set1) // add the data sets

        val dataSets2: ArrayList<IBarDataSet> = ArrayList()
        dataSets2.add(set2) // add the data sets


        var data1 = BarData()
        data1.addDataSet(set1)
        data1.addDataSet(set2)
        set1.color = Color.BLACK

        chart.data = data1
    }
}