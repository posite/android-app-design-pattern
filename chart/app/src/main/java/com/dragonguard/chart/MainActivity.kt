package com.dragonguard.chart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class MainActivity : AppCompatActivity() {
    private lateinit var chart: BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chart = findViewById(R.id.barchart)
        val values1 = ArrayList<BarEntry>()

        for (i in 0 until 10) {
            val value1 = (Math.random() * 10).toFloat()
            val value2 = (Math.random() * 10).toFloat()
            values1.add(BarEntry(i.toFloat(), value1))
        }
        val set1 = BarDataSet(values1, "DataSet 1")

        val dataSets1: ArrayList<IBarDataSet> = ArrayList()
        dataSets1.add(set1) // add the data sets



        val data1 = BarData()
        data1.addDataSet(set1)
        set1.color = Color.BLACK
        chart.apply {
            setDrawValueAboveBar(true)
            setTouchEnabled(false) // 차트 터치 막기
            setPinchZoom(false) // 두손가락으로 줌 설정
            setDrawGridBackground(false) // 격자구조
            description.isEnabled = false // 그래프 오른쪽 하단에 라벨 표시
            legend.isEnabled = true // 차트 범례 설정(legend object chart)
            axisRight.isEnabled = false // 오른쪽 Y축을 안보이게 해줌.
            axisLeft.apply { //왼쪽 축. 즉 Y방향 축을 뜻한다.
                axisMinimum = 0f // 최소값 0
                granularity = 10f // 50 단위마다 선을 그리려고 설정.
                setDrawLabels(true) // 값 적는거 허용 (0, 50, 100)
                setDrawGridLines(true) //격자 라인 활용
                setDrawAxisLine(true) // 축 그리기 설정
                axisLineColor = ContextCompat.getColor(context, R.color.black) // 축 색깔 설정
                gridColor = ContextCompat.getColor(context, R.color.purple_200) // 축 아닌 격자 색깔 설정
                textColor = ContextCompat.getColor(context, R.color.black) // 라벨 텍스트 컬러 설정
                textSize = 13f //라벨 텍스트 크기
            }
            xAxis.apply {
                isEnabled = true
                position = XAxis.XAxisPosition.BOTTOM //X축을 아래에다가 둔다.
                granularity = 1f // 1 단위만큼 간격 두기
                setDrawAxisLine(true) // 축 그림
                setDrawGridLines(false) // 격자
                textColor = ContextCompat.getColor(context, R.color.black) //라벨 색상
                textSize = 12f // 텍스트 크기
            }
        }
        chart.data = data1
    }
}