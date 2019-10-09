package com.cuile.msgforward

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast

class MsgReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.v("MsgReceiver", "Received Message!!!")

        @Suppress("UNCHECKED_CAST")
        val msgs: Array<Any> = intent.extras?.get("pdus") as Array<Any>

        val format = intent.getStringExtra("format")
        for (msg in msgs) {
            val smsMsg = SmsMessage.createFromPdu(msg as ByteArray?, format)
            val from = smsMsg.originatingAddress
            val body = smsMsg.messageBody
            val time = smsMsg.timestampMillis

            Log.v("MsgReceiver", "From:$from\n,Contemt:$body\n,Time:$time\n")
        }

    }
}
