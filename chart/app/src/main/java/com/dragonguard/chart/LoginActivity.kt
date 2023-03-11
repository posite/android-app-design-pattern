package com.dragonguard.chart

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.dragonguard.chart.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private var viewmodel = ViewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onNewIntent(intent: Intent?) {
        Log.d("newintent", "onNewIntent")
        super.onNewIntent(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val result = intent?.data?.getQueryParameter("code")
        Log.d("newintent", "code = $result")
        result?.let{
            val coroutine = CoroutineScope(Dispatchers.IO)
            coroutine.launch {
                Log.d("onNewIntent", "전송!! $it")
                val deffered = coroutine.async ( Dispatchers.IO ) {
                    viewmodel.token(it)
                }
                val result = deffered.await()
                if(result.access_token == null) {
                    Log.d("onnewintent", "실패!!")
                } else {
                    Log.d("onnewintent", "성공!! ${result.access_token}, ${result.scope}, ${result.token_type}")
                    getUserInfo(result.access_token)
                }
            }
        }


        binding.oauthStart.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/login/oauth/authorize?client_id=cc6bd0b5c9696c693ebe&scope=user")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }

    private fun getUserInfo(token: String) {
        val coroutine = CoroutineScope(Dispatchers.IO)
        coroutine.launch {
            val deffered = coroutine.async ( Dispatchers.IO ) {
                viewmodel.getUserInfo(token)
            }
            val result = deffered.await()
            if(result != null) {
                Log.d("result", "id : ${result.login}")
            }
        }
    }
}