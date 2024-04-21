package com.example.helpnearbymobile.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.helpnearbymobile.R
import com.example.helpnearbymobile.common.SharedPreferencesManager
import com.example.helpnearbymobile.databinding.ActivityWelcomeBinding
<<<<<<< Updated upstream
import com.example.helpnearbymobile.presentation.main_ui.NeedlerMainActivity
import com.example.helpnearbymobile.presentation.main_ui.VolonteurMainActivity
import com.google.firebase.messaging.FirebaseMessaging
=======
import com.example.helpnearbymobile.presentation.needler.NeedlerMainActivity
import com.example.helpnearbymobile.presentation.volonteur.VolonteurMainActivity
>>>>>>> Stashed changes

class WelcomeActivity : AppCompatActivity() {
    lateinit var token: String
    lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences("token", MODE_PRIVATE)

        getToken()

        FirebaseMessaging.getInstance().subscribeToTopic("orders")
        

        with(binding) {
            welcomeTextView.animate().apply {
                duration = 2000
                alpha(1.0f)
            }
            btnsLinear.animate().apply {
                duration = 1200
                startDelay = 1000
                alpha(1.0f)
            }

            imvBtn.setOnClickListener { _ ->
                startActivity(Intent(this@WelcomeActivity, VolonteurMainActivity::class.java))
                finish()
            }

            imnBtn.setOnClickListener { _ ->
                startActivity(Intent(this@WelcomeActivity, NeedlerMainActivity::class.java))
                finish()
            }
        }
    }

    fun getToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TOKEN", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            token = task.result
            SharedPreferencesManager.saveToken(prefs, token)

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d("TOKEN", msg)

        }
    }
}