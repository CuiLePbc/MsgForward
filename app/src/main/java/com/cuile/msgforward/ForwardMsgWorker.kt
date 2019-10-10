package com.cuile.msgforward

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class ForwardMsgWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {

        val msgArray = inputData.getStringArray("MyMstContent")

        if (!msgArray.isNullOrEmpty()) {

            val result = withContext(Dispatchers.IO) {
                Log.v(javaClass.simpleName, msgArray[0] + msgArray[1] + msgArray[2])
                val retrofit = Retrofit.Builder()
                    .baseUrl(FeiGeService.BASE_URL)
                    .client(
                        OkHttpClient.Builder()
                            .addInterceptor(
                            HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }).build()
                    )
                    .build()

                val paramsMap = mutableMapOf<String, Any>()
                paramsMap["secret"] = FeiGeService.FEI_GE_SECRET
                paramsMap["template_id"] = FeiGeService.TEMPLATE_ID
                paramsMap["app_key"] = FeiGeService.APP_KEY
                paramsMap["url"] = ""
                paramsMap["data"] = mutableMapOf(
                    "first" to mutableMapOf("value" to "first", "color" to "#173177"),
                    "keyword1" to mutableMapOf("value" to msgArray[1], "color" to "#173177"),
                    "keyword2" to mutableMapOf("value" to msgArray[0], "color" to "#173177"),
                    "keyword3" to mutableMapOf("value" to msgArray[2], "color" to "#173177"),
                    "remark" to mutableMapOf("value" to "remark", "color" to "#173177")
                )

                val requestBody = Gson().toJson(paramsMap).toRequestBody("application/json".toMediaTypeOrNull())
                val sendMsgResult = retrofit.create(FeiGeService::class.java)
                    .sendMessage(requestBody)

                return@withContext workDataOf("FordwardMsgWorkerResult" to sendMsgResult.string())
            }

            return Result.success(result)
        }

        return Result.failure()
    }
}