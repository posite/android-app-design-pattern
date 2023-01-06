package com.dragonguard.mvvmpractice


import androidx.lifecycle.MutableLiveData

//뷰모델 구현
class ViewModel {

    var toastMessage = MutableLiveData<Int>()
    var truePasswordMessage = MutableLiveData<Boolean>()
    var falsePasswordMessage = MutableLiveData<Boolean>()

    var model = Model()

    fun clickNumber(i: Int){
        toastMessage.value=i
        model.inputPassword(i)
        if(model.password.size==4){
            if(model.checkPassword()){
                truePasswordMessage.value = true
                model.clearPassword()
            }else{
                model.clearPassword()
                falsePasswordMessage.value = true
            }
        }
    }
}