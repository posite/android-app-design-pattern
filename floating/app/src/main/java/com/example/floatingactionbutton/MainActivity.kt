package com.example.floatingactionbutton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.PopupWindow
import com.example.floatingactionbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val popupView = LayoutInflater.from(this).inflate(R.layout.fab_description, null)
        val popup = PopupWindow(
            popupView,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        ).apply {
            isFocusable = true
            elevation = 10f
        }

        binding.writeMemoBtn.setOnClickListener {
            val intent = Intent(applicationContext, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.writeMemoBtn.setOnLongClickListener {
            popup.showAsDropDown(binding.writeMemoBtn, -100, -(binding.writeMemoBtn.height+150))
            Handler(Looper.getMainLooper()).postDelayed({
                if (popup.isShowing) {
                    popup.dismiss()
                }
            }, 3000)
            true
        }
    }
}