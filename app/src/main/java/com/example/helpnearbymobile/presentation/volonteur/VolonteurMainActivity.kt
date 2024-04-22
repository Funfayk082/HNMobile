package com.example.helpnearbymobile.presentation.main_ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.helpnearbymobile.R
import com.example.helpnearbymobile.data.repositories.Storage
import com.example.helpnearbymobile.domain.viewmodel.VolonteurViewModel
import com.google.firebase.messaging.FirebaseMessaging

class VolonteurMainActivity : AppCompatActivity() {
    val volonteurViewModel by lazy { ViewModelProvider(this)[VolonteurViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.shared_prefs_key_order))
            .addOnCompleteListener { task ->
                var msg = getString(R.string.connected)
                if (!task.isSuccessful) {
                    msg = getString(R.string.connection_error)
                }
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            } // TODO: Перенести в логику с авторизацией

        Storage.observableOfMessage.observe(this) {
            if (it) {
                volonteurViewModel.getOrders("Кострома") // TODO: Название города будет браться от пользователя
                volonteurViewModel.orders.observe(this@VolonteurMainActivity) {
                    if (it.isNullOrEmpty()) {
                        // TODO: Отобразить сообщение об отсутствии заказов
                    } else {
                        // TODO: Отобразить полученные заказы на UI
                    }
                }
            }
        }


    }
}