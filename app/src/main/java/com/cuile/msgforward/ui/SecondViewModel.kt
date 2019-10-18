package com.cuile.msgforward.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuile.msgforward.ForwardInfo
import com.cuile.msgforward.getAppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondViewModel(val appContext: Context): ViewModel() {
    val msgHistorys: LiveData<MutableList<ForwardInfo>> = MutableLiveData<MutableList<ForwardInfo>>().apply {
        value = mutableListOf()
    }

    init {
        refreshMsgHistorys()
    }

    fun refreshMsgHistorys() {
        viewModelScope.launch(Dispatchers.Main) {
            msgHistorys.value?.clear()
            val msgs = withContext(Dispatchers.IO) {
                getAppDataBase(appContext).forwardMsgDao().getAllForwardMsgs()
            }
            msgHistorys.value?.addAll(msgs)
        }
    }
}