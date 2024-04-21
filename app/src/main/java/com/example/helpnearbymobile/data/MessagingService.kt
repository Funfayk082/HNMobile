package com.example.helpnearbymobile.data

<<<<<<< Updated upstream:app/src/main/java/com/example/helpnearbymobile/common/MessagingService.kt
import android.os.Parcel
import android.os.Parcelable
import com.example.helpnearbymobile.data.repositories.Storage
=======
>>>>>>> Stashed changes:app/src/main/java/com/example/helpnearbymobile/data/MessagingService.kt
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