package com.dragonguard.mvvmpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dragonguard.mvvmpractice.databinding.ActivityMainBinding


//뷰모델 부분
class MainActivity : AppCompatActivity(){
    lateinit var binding : ActivityMainBinding

    var viewModel = ViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        viewModel.toastMessage.observe(this, Observer { i->
            Toast.makeText(this,"$i 번을 클릭했습니다.",Toast.LENGTH_SHORT).show()
            binding.password.visibility = View.VISIBLE
            binding.password.append("* ")
        })
        viewModel.truePasswordMessage.observe(this, Observer {
            if(viewModel.truePasswordMessage.value == true){
                binding.success.text = "비밀번호 인증 성공!!"
                binding.password.text = ""
                binding.success.visibility = View.VISIBLE
                binding.password.visibility = View.INVISIBLE
            }
        })
        viewModel.falsePasswordMessage.observe(this, Observer {
            if(viewModel.truePasswordMessage.value == true){
                binding.success.text = "비밀번호 틀림!! 다시 입력하세요!!"
                binding.password.text = ""
                binding.success.visibility = View.VISIBLE
                binding.password.visibility = View.INVISIBLE
            }
        })
    }

}