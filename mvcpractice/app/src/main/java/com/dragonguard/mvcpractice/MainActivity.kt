package com.dragonguard.mvcpractice

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dragonguard.mvcpractice.databinding.ActivityMainBinding

//컨트롤러 부분
class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    var model = Model()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
    }
    fun clickNumber(i: Int){
        Toast.makeText(this,"$i 번을 클릭했습니다.",Toast.LENGTH_SHORT).show()
        model.inputPassword(i)
        if(model.password.size==4){
            if(model.checkPassword()){
                binding.success.text = "비밀번호 인증 성공!!"
                binding.success.visibility = View.VISIBLE
                model.clearPassword()
            }else{
                model.clearPassword()
                binding.success.text = "비밀번호 틀림!! 다시 입력하세요!!"
                binding.success.visibility = View.VISIBLE
            }
        }
    }
}