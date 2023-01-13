package com.dragonguard.mvvmpractice
// 모델 부분
class Model {
    var password: MutableList<Int> = mutableListOf()
    private var savePassword = mutableListOf(3, 7, 6, 0)
    fun inputPassword(i: Int) {
        if (password.size < 4) {
            password.add(i)
        }
    }
    fun checkPassword(): Boolean {
        var trueCount = 0
        for (j in 0 until savePassword.size) {
            if (savePassword[j] == password[j]) {
                trueCount++
            }
        }
        return trueCount == 4
    }
    fun deletePassword() {
        password.removeLast()
    }
    fun clearPassword() {
        password = mutableListOf()
    }
    fun changePassword() {
        if (password.size == savePassword.size) {
            savePassword = mutableListOf()
            savePassword.addAll(password)
        }
    }
}
