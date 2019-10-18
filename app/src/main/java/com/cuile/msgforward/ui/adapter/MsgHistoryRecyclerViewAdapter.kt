package com.cuile.msgforward.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuile.msgforward.ForwardInfo
import com.cuile.msgforward.R

class MsgHistoryRecyclerViewAdapter(private val context: Context, private val datas: List<ForwardInfo>) : RecyclerView.Adapter<MsgHistoryRecyclerViewAdapter.MyMsgHistoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMsgHistoryViewHolder =
        MyMsgHistoryViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_msghistory_recyclerviewer,
                parent,
                false
            )
        )


    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: MyMsgHistoryViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    class MyMsgHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val fromTextView: TextView = view.findViewById(R.id.msgFromTextView)
        private val timeTextView: TextView = view.findViewById(R.id.msgTimeTextView)
        private val contentTextView: TextView = view.findViewById(R.id.msgContentPreTextView)

        fun bind(forwardInfo: ForwardInfo) {
            fromTextView.text = forwardInfo.from
            timeTextView.text = forwardInfo.sendTime
            contentTextView.text = forwardInfo.content
        }
    }
}
