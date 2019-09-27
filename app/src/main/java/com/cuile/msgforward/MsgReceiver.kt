package com.cuile.msgforward

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast

class MsgReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Received msg!", Toast.LENGTH_SHORT).show()
        val msgs: Array<Any> = intent.extras?.get("pdus") as Array<Any>
        for (msg in msgs) {
            val smsMsg = SmsMessage.createFromPdu(msg as ByteArray?, "")
            val from = smsMsg.originatingAddress
            val body = smsMsg.messageBody
            val time = smsMsg.timestampMillis

            Toast.makeText(context, "From:$from\n,Contemt:$body\n,Time:$time\n", Toast.LENGTH_LONG).show()
        }

    }
}
