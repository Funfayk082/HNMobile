package com.example.helpnearbymobile.common

import android.os.Parcel
import android.os.Parcelable
import com.example.helpnearbymobile.data.repositories.Storage
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService() : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            handleNow(remoteMessage.data.toString())
        }
    }


    fun handleNow(msg: String?) {
        Storage.setMessageIsDelivered(true)
    }
}