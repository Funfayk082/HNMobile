package com.example.helpnearbymobile.presentation.volonteur

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
<<<<<<< Updated upstream:app/src/main/java/com/example/helpnearbymobile/presentation/main_ui/VolonteurMainActivity.kt
import com.example.helpnearbymobile.data.repositories.Storage
import com.example.helpnearbymobile.domain.viewmodel.VolonteurViewModel
=======
import com.example.helpnearbymobile.R
import com.example.helpnearbymobile.data.Storage
>>>>>>> Stashed changes:app/src/main/java/com/example/helpnearbymobile/presentation/volonteur/VolonteurMainActivity.kt
import com.google.firebase.messaging.FirebaseMessaging

class VolonteurMainActivity : AppCompatActivity() {
    val volonteurViewModel by lazy { ViewModelProvider(this)[VolonteurViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().subscribeToTopic("orders")
            .addOnCompleteListener { task ->
                var msg = "Соединено с сервером"
                if (!task.isSuccessful) {
                    msg = "Соединение не установлено"
                }
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            } // TODO: Перенести в логику с авторизацией

        Storage.observableOfMessage.observe(this) {
            if (it) {
                volonteurViewModel.getOrders("Кострома")
                volonteurViewModel.orders.observe(this@VolonteurMainActivity) {
                    if (it.isNullOrEmpty()) {
                        Log.d("ORDER", "Empty")
                    } else {
                        it.forEach {
                            Log.d("ORDER", it.toString())
                        }
                    }
                }
            }
        }


    }
}