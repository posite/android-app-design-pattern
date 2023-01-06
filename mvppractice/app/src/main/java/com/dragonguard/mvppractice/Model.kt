package com.dragonguard.mvppractice
//모델 부분
class Model {
    var password : MutableList<Int> = mutableListOf()
    fun inputPassword(i: Int){
        if(password.size<4){
            password.add(i)
        }
    }
    fun checkPassword() : Boolean{
        var trueCount = 0
        var savePassword = mutableListOf(3,7,6,0)
        for(j in 0 until savePassword.size){
            if(savePassword.get(j)==password.get(j)){
                trueCount++
            }
        }
        return trueCount==4
    }
    fun clearPassword(){
        password= mutableListOf()
    }
}