package com.dragonguard.mvppractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dragonguard.mvppractice.databinding.ActivityMainBinding


//인터페이스 부분
class MainActivity : AppCompatActivity(), ViewInterface{
    lateinit var binding : ActivityMainBinding
    var presenter = Presenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
    }
    fun clickNumber(i: Int){
        presenter.clickNumber(i)
    }

    override fun toastMessage(i: Int) {
        Toast.makeText(this,"$i 번을 클릭했습니다.",Toast.LENGTH_SHORT).show()
    }

    override fun truePasswordMessage() {
        binding.success.text = "비밀번호 인증 성공!!"
        binding.success.visibility = View.VISIBLE
    }

    override fun falsePasswordMessage() {
        binding.success.text = "비밀번호 틀림!! 다시 입력하세요!!"
        binding.success.visibility = View.VISIBLE
    }


}