package com.dragonguard.mvvmpractice

import androidx.lifecycle.MutableLiveData

// 뷰모델 구현
class ViewModel {

    var toastMessage = MutableLiveData<Int>()
    var truePasswordMessage = MutableLiveData<Boolean>()
    var falsePasswordMessage = MutableLiveData<Boolean>()
    var deletePasswordMessage = MutableLiveData<Boolean>()
    var changePasswordMessage = MutableLiveData<Boolean>()
    var changeFinishedMessage = MutableLiveData<Boolean>()
    var model = Model()
    private var change = false

    fun clickNumber(i: Int) {
        toastMessage.value = i
        model.inputPassword(i)
        if (model.password.size == 4) {
            if (!change) {
                if (model.checkPassword()) {
                    truePasswordMessage.value = true
                    model.clearPassword()
                } else {
                    falsePasswordMessage.value = true
                    model.clearPassword()
                }
            } else {
                model.changePassword()
                change = false
                changeFinishedMessage.value = true
                model.clearPassword()
            }
        }
    }
    fun clickCancel() {
        if (model.password.size > 0) {
            model.deletePassword()
            deletePasswordMessage.value = true
        }
    }
    fun clickChange() {
        model.clearPassword()
        change = true
        changePasswordMessage.value = true
    }
}
