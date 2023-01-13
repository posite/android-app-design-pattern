package com.dragonguard.mvvmpractice

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dragonguard.mvvmpractice.databinding.ActivityMainBinding

// 인터페이스 부분
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var viewModel = ViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        viewModel.toastMessage.observe(
            this,
            Observer { i ->
                binding.success.visibility = View.INVISIBLE
                Toast.makeText(this, "$i 번을 클릭했습니다.", Toast.LENGTH_SHORT).show()
                binding.password.visibility = View.VISIBLE
                binding.password.append("* ")
            }
        )
        viewModel.truePasswordMessage.observe(
            this,
            Observer {
                if (viewModel.truePasswordMessage.value == true) {
                    binding.success.text = "비밀번호 인증 성공!!"
                    binding.password.text = ""
                    binding.success.visibility = View.VISIBLE
                    binding.password.visibility = View.INVISIBLE
                }
            }
        )
        viewModel.falsePasswordMessage.observe(
            this,
            Observer {
                if (viewModel.falsePasswordMessage.value == true) {
                    binding.success.text = "비밀번호 틀림!! 다시 입력하세요!!"
                    binding.password.text = ""
                    binding.success.visibility = View.VISIBLE
                    binding.password.visibility = View.INVISIBLE
                }
            }
        )
        viewModel.deletePasswordMessage.observe(this) {
            if (viewModel.deletePasswordMessage.value == true) {
                var text = binding.password.text
                binding.password.text = text.substring(0, (text.length - 2))
            }
        }
        viewModel.changePasswordMessage.observe(this) {
            if (viewModel.changePasswordMessage.value == true) {
                binding.success.text = "변경할 비밀번호 입력"
                binding.success.visibility = View.VISIBLE
                binding.password.visibility = View.VISIBLE
            }
        }
        viewModel.changeFinishedMessage.observe(this) {
            if (viewModel.changeFinishedMessage.value == true) {
                binding.success.text = "비밀번호 변경 완료!!"
                binding.password.text = ""
                binding.password.visibility = View.INVISIBLE
                binding.success.visibility = View.VISIBLE
            }
        }
    }
}
