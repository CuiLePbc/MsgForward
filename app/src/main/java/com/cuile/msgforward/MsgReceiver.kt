package com.cuile.msgforward

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.text.format.DateFormat
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf

class MsgReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.v("MsgReceiver", "Received Message!!!")

        @Suppress("UNCHECKED_CAST")
        val msgs: Array<Any> = intent.extras?.get("pdus") as Array<Any>

        val format = intent.getStringExtra("format")
        for (msg in msgs) {
            val smsMsg = SmsMessage.createFromPdu(msg as ByteArray?, format)
            val from = smsMsg.originatingAddress.toString()
            val body = smsMsg.messageBody
            val time = DateFormat.format("yyyy-MM-dd HH:mm:ss", smsMsg.timestampMillis).toString()

            val msgData = workDataOf("MyMstContent" to arrayOf(from, body, time))
            val forwardMsgWork =
                OneTimeWorkRequestBuilder<ForwardMsgWorker>()
                    .setInputData(msgData).addTag("SendMsgByInternet")
                    .build()
            WorkManager.getInstance(context).enqueue(forwardMsgWork)

            WorkManager.getInstance(context).getWorkInfoByIdLiveData(forwardMsgWork.id)
                .observeForever {
                    if (it != null && it.state == WorkInfo.State.SUCCEEDED) {
                        val result = it.outputData.getStringArray("FEI_GE_SEND_RESULT") ?: arrayOf("", "", "")
                        Log.v(this.javaClass.simpleName, result[0] + result[1] + result[2])
                    }
                }
        }

    }
}
