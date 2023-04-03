package com.dragonguard.chart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class ViewModel: androidx.lifecycle.ViewModel() {
    private val repository = APIRepository()

    val customTimerDuration: MutableLiveData<Long> = MutableLiveData(MIllIS_IN_FUTURE)
    private var oldTimeMills: Long = 0

    companion object {
        const val MIllIS_IN_FUTURE = 300000L
        const val TICK_INTERVAL = 1000L
    }

    val timerJob: Job = viewModelScope.launch(start = CoroutineStart.LAZY) {
        withContext(Dispatchers.IO) {
            oldTimeMills = System.currentTimeMillis()
            while (customTimerDuration.value!! > 0L) {
                val delayMills = System.currentTimeMillis() - oldTimeMills
                if (delayMills == TICK_INTERVAL) {
                    customTimerDuration.postValue(customTimerDuration.value!! - delayMills)
                    oldTimeMills = System.currentTimeMillis()
                }
            }
        }
    }

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