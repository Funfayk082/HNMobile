package com.example.helpnearbymobile.presentation.auth_ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.helpnearbymobile.databinding.ActivityAuthBinding
import com.example.helpnearbymobile.domain.model.ChoosenPage

class AuthActivity : AppCompatActivity() {
    private lateinit var frameLayout: FrameLayout
    private lateinit var choosenPage: ChoosenPage
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        choosenPage = intent.getSerializableExtra("ChoosenPage") as ChoosenPage;
        val binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentTransaction = supportFragmentManager.beginTransaction()

        setFrame(binding)
    }

    fun setFrame(binding: ActivityAuthBinding) {
        with(binding) {
            frameLayout

            when (choosenPage) {
                ChoosenPage.LOGIN -> fragmentTransaction.replace(
                    frameLayout.id,
                    LoginFragment()
                )

                ChoosenPage.REGISTER -> fragmentTransaction.replace(
                    frameLayout.id,
                    RegisterFragment()
                )

            }

            fragmentTransaction.commit()
        }
    }
}