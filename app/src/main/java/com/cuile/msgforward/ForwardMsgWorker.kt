package com.cuile.msgforward

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class ForwardMsgWorker(private val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {

        val msgArray = inputData.getStringArray("MyMstContent")

        if (!msgArray.isNullOrEmpty()) {

            val result = withContext(Dispatchers.IO) {
                Log.v(javaClass.simpleName, msgArray[0] + msgArray[1] + msgArray[2])

                val sendMsgResult = sendByFeige(msgArray[0], msgArray[1], msgArray[2])

                val resultCode = Gson().fromJson(sendMsgResult.string(), FeiGeResponseBody::class.java).code

                return@withContext resultCode == 200
            }

            return if (result) {
                Result.success(workDataOf("FEI_GE_SEND_RESULT" to msgArray))
            } else {
                Result.failure()
            }
        }

        return Result.failure()
    }

    private suspend fun sendByFeige(from: String, content: String, time: String): ResponseBody {
        val paramsMap = mutableMapOf<String, Any>()
        paramsMap["secret"] = FeiGeService.FEI_GE_SECRET
        paramsMap["template_id"] = FeiGeService.TEMPLATE_ID
        paramsMap["app_key"] = FeiGeService.APP_KEY
        paramsMap["url"] = ""
        paramsMap["data"] = mutableMapOf(
            "first" to mutableMapOf("value" to "first", "color" to "#173177"),
            "keyword1" to mutableMapOf("value" to content, "color" to "#173177"),
            "keyword2" to mutableMapOf("value" to from, "color" to "#173177"),
            "keyword3" to mutableMapOf("value" to time, "color" to "#173177"),
            "remark" to mutableMapOf("value" to "remark", "color" to "#173177")
        )

        val requestBody = Gson().toJson(paramsMap).toRequestBody("application/json".toMediaTypeOrNull())

        return Retrofit.Builder()
            .baseUrl(FeiGeService.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }).build()
            )
            .build().create(FeiGeService::class.java)
            .sendMessage(requestBody)
    }

    private suspend fun sendByFangTang(from: String, content: String, time: String): ResponseBody {
        val paramsMap = mutableMapOf<String, String>(
            "text" to "From:$from,At:$time",
            "desp" to content
        )

        return Retrofit.Builder()
            .baseUrl(FangTangService.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }).build()
            )
            .build().create(FangTangService::class.java)
            .sendMessage(paramsMap)
    }
}