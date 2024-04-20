package com.example.helpnearbymobile.presentation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helpnearbymobile.databinding.ActivityWelcomeBinding
import com.example.helpnearbymobile.presentation.main_ui.NeedlerMainActivity
import com.example.helpnearbymobile.presentation.main_ui.VolonteurMainActivity

class WelcomeActivity : AppCompatActivity() {
    lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences("token", MODE_PRIVATE)

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
}