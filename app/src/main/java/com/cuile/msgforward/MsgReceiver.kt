package com.cuile.msgforward

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.telephony.SmsMessage
import android.text.format.DateFormat
import android.util.Log
import android.util.TimeUtils
import android.widget.Toast
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.*

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
            val forwardMsgWorkRequest =
                OneTimeWorkRequestBuilder<ForwardMsgWorker>()
                    .setInputData(msgData)
                    .build()
            WorkManager.getInstance(context).enqueue(forwardMsgWorkRequest)
        }

    }
}
