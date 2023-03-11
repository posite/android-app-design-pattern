package com.dragonguard.chart

class ViewModel {
    private val repository = APIRepository()

    fun auth(clientId: String): Boolean {
        return repository.getAuthorization(clientId)
    }

    fun token(code: String): TokenModel{
        return repository.getAccessToken(code)
    }

    fun getUserInfo(token: String): UserInfoModel?{
        return repository.getUserInfo(token)
    }
}