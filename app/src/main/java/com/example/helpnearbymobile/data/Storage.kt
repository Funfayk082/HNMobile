package com.example.helpnearbymobile.data

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Storage {
    private val _messageIsDelivered = MutableLiveData(false)
    val observableOfMessage: LiveData<Boolean> = _messageIsDelivered

    fun setMessageIsDelivered(bool: Boolean) {
        val handler = Handler(Looper.getMainLooper());
        handler.post { _messageIsDelivered.value = bool }
    }
}