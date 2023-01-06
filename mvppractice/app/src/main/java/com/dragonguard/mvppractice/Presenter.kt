package com.dragonguard.mvppractice

import android.view.View
import android.widget.Toast

class Presenter(var viewInterface: ViewInterface) {
    var model = Model()

    fun clickNumber(i: Int){
        viewInterface.toastMessage(i)
        model.inputPassword(i)
        if(model.password.size==4){
            if(model.checkPassword()){
                viewInterface.truePasswordMessage()
                model.clearPassword()
            }else{
                model.clearPassword()
                viewInterface.falsePasswordMessage()
            }
        }
    }
}