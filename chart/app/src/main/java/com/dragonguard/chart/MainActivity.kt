package com.dragonguard.chart

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import com.dragonguard.chart.ViewModel.Companion.MIllIS_IN_FUTURE
import com.dragonguard.chart.ViewModel.Companion.TICK_INTERVAL
import com.dragonguard.chart.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope.coroutineContext

class MainActivity : AppCompatActivity() {
    private lateinit var chart: RadarChart
    private lateinit var userManager: UserDataStore
    private lateinit var binding: ActivityMainBinding
    private var id = ""
    private var password = ""
    private lateinit var timer: CountDownTimer
    private var viewmodel = ViewModel()

    //    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

//    override fun onNewIntent(intent: Intent?) {
//        Log.d("newintent", "onNewIntent")
//        super.onNewIntent(intent)
//        val result = intent?.data?.getQueryParameter("code")
//        Log.d("newintent", "code = $result")
//        result?.let{
//            val coroutine = CoroutineScope(Dispatchers.IO)
//            coroutine.launch {
//                Log.d("onNewIntent", "전송!! $it")
//                val deffered = coroutine.async ( Dispatchers.IO ) {
//                    viewmodel.token(it)
//                }
//                val result = deffered.await()
//                if(result.access_token == null) {
//                    Log.d("onnewintent", "실패!!")
//                } else {
//                    Log.d("onnewintent", "성공!! ${result.access_token}, ${result.scope}, ${result.token_type}")
//                    getUserInfo(result.access_token)
//                }
//            }
//        }
//    }

    //    private fun getUserInfo(token: String) {
//        val coroutine = CoroutineScope(Dispatchers.IO)
//        coroutine.launch {
//            val deffered = coroutine.async ( Dispatchers.IO ) {
//                viewmodel.getUserInfo(token)
//            }
//            val result = deffered.await()
//            if(result != null) {
//                Log.d("result", "id : ${result.login}")
//            }
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewmodel

        chart = findViewById(R.id.barchart)
        val values1 = ArrayList<RadarEntry>()
        val values2 = ArrayList<RadarEntry>()

        val intent = intent


//        binding.click.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }


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
        setUpCountDownTimer()
        timer.start()

    }

    private fun setUpCountDownTimer() {
        timer = object : CountDownTimer(MIllIS_IN_FUTURE, TICK_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                val minute = millisUntilFinished/60000L
                val second = millisUntilFinished%60000L/1000L
                binding.remainTime.text = "$minute:$second"
                viewmodel.timerJob.start()
            }

            override fun onFinish() {
            }
        }
    }

    private fun checkNetworkState() {
        if (NetworkCheck.checkNetworkState(applicationContext)) {
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