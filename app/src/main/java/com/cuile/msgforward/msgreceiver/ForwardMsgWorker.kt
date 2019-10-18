package com.cuile.msgforward.msgreceiver

import android.content.Context
import android.text.format.DateFormat
import android.text.format.DateUtils
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.cuile.msgforward.ForwardInfo
import com.cuile.msgforward.getAppDataBase
import com.google.gson.Gson
import com.zjiecode.wxpusher.client.WxPusher
import com.zjiecode.wxpusher.client.bean.Message
import com.zjiecode.wxpusher.client.bean.MessageResult
import com.zjiecode.wxpusher.client.bean.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ForwardMsgWorker(private val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {

        val msgArray = inputData.getStringArray("MyMstContent")

        if (!msgArray.isNullOrEmpty()) {

            val result = withContext(Dispatchers.IO) {

                var sendResult = false

                val feigeResult = sendByFeige(msgArray[0], msgArray[1], msgArray[2])

                if (feigeResult.code == 200) {
                    sendResult = true
                    // send successful
                    saveMsgIntoDb(ForwardInfo(
                        from = msgArray[0],
                        to = "",
                        content = msgArray[1],
                        sendTime = msgArray[2],
                        forwardTime = DateFormat.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis()).toString(),
                        forwardType = "FeiGeKuaiXin",
                        other = ""
                    ))
                } else {
                    if(sendByWxpusher(msgArray[0], msgArray[1], msgArray[2])) {
                        sendResult = true
                        saveMsgIntoDb(ForwardInfo(
                            from = msgArray[0],
                            to = "",
                            content = msgArray[1],
                            sendTime = msgArray[2],
                            forwardTime = DateFormat.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis()).toString(),
                            forwardType = "WxPusher",
                            other = ""
                        ))
                    }
                }

                return@withContext sendResult
            }

            return if (result) {
                Result.success(workDataOf("FEI_GE_SEND_RESULT" to msgArray))
            } else {
                Result.failure()
            }
        }

        return Result.failure()
    }

    private fun sendByWxpusher(from: String, msgContent: String, time: String): Boolean {
        val msg = Message().apply {
            appToken = "AT_cMhwhbigR83TEWSBKvYchPduGRDVh9Vv"
            contentType = Message.CONTENT_TYPE_TEXT
            content = "From:$from,At:$time\n$msgContent"
            uids = setOf("UID_djhDzbyOv4l8q5F59MnUS3UIPSA6")
            url = "http://wxpuser.zjiecode.com"
        }
        val result: com.zjiecode.wxpusher.client.bean.Result<List<MessageResult>> = WxPusher.send(msg)
        return result.isSuccess
    }

    private fun saveMsgIntoDb(forwardInfo: ForwardInfo) {
        val forwardMsgDao = getAppDataBase(appContext).forwardMsgDao()
        forwardMsgDao.insertForwardMsg(forwardInfo)
    }

    private suspend fun sendByFeige(from: String, content: String, time: String): FeiGeResponseBody {
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
            .addConverterFactory(GsonConverterFactory.create())
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