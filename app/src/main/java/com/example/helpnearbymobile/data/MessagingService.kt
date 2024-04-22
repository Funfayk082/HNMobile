package com.example.helpnearbymobile.common

import com.example.helpnearbymobile.data.repositories.Storage
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService() : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            handleNow(remoteMessage.data.toString())
        }
    }


    private fun handleNow(msg: String?) {
        Storage.setMessageIsDelivered(true)
    }
}