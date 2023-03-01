package com.dragonguard.chart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ColorFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var chart: RadarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chart = findViewById(R.id.barchart)
        val values1 = ArrayList<RadarEntry>()
        val values2 = ArrayList<RadarEntry>()

        for (i in 0 until 3) {
            val value1 = (Math.random() * 10).toFloat()
            val value2 = (Math.random() * 10).toFloat()
            values1.add(RadarEntry(value1))
            values2.add(RadarEntry(value2))
        }
        val set1 = RadarDataSet(values1, "DataSet 1")
        set1.fillColor = Color.BLACK
        set1.setDrawFilled(true)
        val set2 = RadarDataSet(values2, "DataSet 2")
        set2.setDrawFilled(true)

        val dataSets1: ArrayList<IRadarDataSet> = ArrayList()
        val dataSets2: ArrayList<IRadarDataSet> = ArrayList()
        dataSets1.add(set1) // add the data sets
        dataSets2.add(set2) // add the data sets

        val data1 = RadarData(dataSets1)
        data1.addDataSet(set2)
        data1.setDrawValues(false)
        set1.color = Color.BLACK
        chart.apply {
            setTouchEnabled(false) // 차트 터치 막기
            description.isEnabled = false // 그래프 오른쪽 하단에 라벨 표시
            legend.isEnabled = true // 차트 범례 설정(legend object chart)
            xAxis.apply {
                isEnabled = true
                position = XAxis.XAxisPosition.BOTTOM //X축을 아래에다가 둔다.
                setDrawAxisLine(true) // 축 그림
                setDrawGridLines(false) // 격자
                textColor = ContextCompat.getColor(context, R.color.black) //라벨 색상
                valueFormatter = CodeFormatter()
                textSize = 12f // 텍스트 크기
            }
        }
        chart.data = data1

        val arr1 : MutableList<String> = mutableListOf("선택하세요", "posite")
        val spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr1)
        val spinner = findViewById<Spinner>(R.id.compare_user_spinner1)
        spinner.adapter = spinnerAdapter
        spinner.setSelection(1)
        if(NetworkCheck.checkNetworkState(this)) {
            Toast.makeText(applicationContext, "인터넷 연결됨!!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "인터넷 연결 안됨!!", Toast.LENGTH_SHORT).show()
        }
        
        val coroutine = CoroutineScope(Dispatchers.Main)
        coroutine.launch {
            while(true) {
                checkNetworkState()
                delay(10000L)
            }
        }

    }

    private fun checkNetworkState() {
        if(NetworkCheck.checkNetworkState(applicationContext)) {
            Toast.makeText(applicationContext, "인터넷 연결됨!!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "인터넷 연결 안됨!!", Toast.LENGTH_SHORT).show()
        }
    }

    class CodeFormatter() : ValueFormatter() {
        private val days = listOf("addition average", "deletion average", "language minimum")
        //        private val days = listOf( "additions", "deletions")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return days.getOrNull(value.toInt()) ?: value.toString()
        }
        override fun getFormattedValue(value: Float): String {
            return "" + value.toInt()
        }
    }
}