package com.dragonguard.chart

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class APIRepository {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .build()

    private val retrofit = Retrofit.Builder().baseUrl(BuildConfig.api)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api = retrofit.create(APIs::class.java)

    fun getAccessToken(code: String):TokenModel {
        val tokenResult = TokenModel(null,null,null)
        val queryMap = mutableMapOf<String, String>()
        queryMap.put("client_id", "cc6bd0b5c9696c693ebe")
        queryMap.put("client_secret", "93be2eea5a7edd91ce91649864bca5a1b60a913a")
        queryMap.put("code", code)
        val token = api.getAccessToken(queryMap)
        return try{
            val result = token.execute()
            result.body()!!
        } catch (e: Exception) {
            tokenResult
        }
    }

    fun getAuthorization(clientId: String): Boolean {
        val queryMap = mutableMapOf<String, String>()
        queryMap.put("client_id", clientId)
        val auth = api.getAuthorization(queryMap)
        return try {
            val result = auth.execute()
            result.isSuccessful
        }catch (e: Exception) {
            false
        }
    }

    fun getUserInfo(token: String): UserInfoModel?{
        var userInfoes: UserInfoModel
        val okhttp = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()

        val retro = Retrofit.Builder().baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apis = retro.create(APIs::class.java)
        val userInfo = apis.getUserInfo("token $token")
        try{
            val result = userInfo.execute()
            if(result.isSuccessful) {
                userInfoes = result.body()!!
                return userInfoes
            }
        } catch(e: Exception) {
            return null
        }
        return null
    }
}