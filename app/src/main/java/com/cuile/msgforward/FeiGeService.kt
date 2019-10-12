package com.cuile.msgforward

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*
import java.util.HashMap

interface FeiGeService {

    companion object {
        const val BASE_URL = "http://u.ifeige.cn/"
        const val FEI_GE_SECRET = "f4f5abb5234257b4c40d748d1543298a"
        const val TEMPLATE_ID = "5uZIvSm5GAdUR1X25HNpjuOp6jSiL88v4opn4a4GLa0"
        const val APP_KEY = "a2a6bcddad127f223cc1b6bcd74b1669"
    }

    @Headers(
        "Host: u.ifeige.cn",
        "Content-Type: application/json"
    )
    @POST("api/message/send")
    suspend fun sendMessage(@Body requestBody: RequestBody): ResponseBody

}

interface FangTangService {

    companion object {
        const val BASE_URL = "https://sc.ftqq.com/"
        const val SC_KEY = "SCU49776T2a19f9f81f181cc78be559a0017bd13f5cc27bbaa2b30"
    }

    @GET("$SC_KEY.send")
    suspend fun sendMessage(@QueryMap map: MutableMap<String, String>): ResponseBody

}