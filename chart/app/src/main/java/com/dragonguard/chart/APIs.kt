package com.dragonguard.chart

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIs {
    @GET("authorize")
    fun getAuthorization(@QueryMap query: Map<String, String>): Call<Unit>

    @POST("access_token")
    @Headers("accept: application/json", "content-type: application/json")
    fun getAccessToken(@QueryMap query: Map<String, String>): Call<TokenModel>

    @POST("user")
    @Headers("accept: application/json", "content-type: application/json")
    fun getUserInfo(@Header("Authorization")token: String): Call<UserInfoModel>
}