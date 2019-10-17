package com.cuile.msgforward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondViewModel: ViewModel() {
    val msgHistorys: LiveData<MutableList<ForwardInfo>> = MutableLiveData<MutableList<ForwardInfo>>()

    init {
        // TODO: search datas from SQL.
        val datas = listOf<ForwardInfo>()
        msgHistorys.value?.addAll(datas)
    }

    fun refreshMsgHistorys() {
        msgHistorys.value?.clear()
        msgHistorys.value?.addAll(mutableListOf())
    }
}