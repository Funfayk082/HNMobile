package com.example.helpnearbymobile.presentation.main_ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.helpnearbymobile.R
import com.example.helpnearbymobile.common.SharedPreferencesManager
import com.example.helpnearbymobile.databinding.ActivityNeedlerMainBinding
import com.example.helpnearbymobile.domain.model.CreateOrderDto
import com.example.helpnearbymobile.domain.viewmodel.NeedlerViewModel

class NeedlerMainActivity : AppCompatActivity() {
    var city: String? = null
    var address: String? = null

    lateinit var prefs: SharedPreferences

    val needlerViewModel: NeedlerViewModel by lazy {
        ViewModelProvider(this)[NeedlerViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNeedlerMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            prefs = getSharedPreferences(getString(R.string.shared_prefs_key_order), MODE_PRIVATE)
            val data = SharedPreferencesManager.getOrderDataFromJson(prefs, getString(R.string.orderdata_key))
            if (data.equals(null)) {
                createChangeView(this)
            }
            else {
                cityNameTextView.text = data.cityName
                city = data.cityName
                addressTextView.text = data.address
                address = data.address
            }
            needHelpBtn.setOnClickListener { _ ->
                needlerViewModel.postOrder(
                    CreateOrderDto(
                        city!!.trim(),
                        commentaryInput.toString().trim(),
                        getString(R.string.not_accepted),
                        "",
                        address!!.trim()
                    ), this@NeedlerMainActivity
                )

                needlerViewModel.orderId.observe(this@NeedlerMainActivity) { orderId ->
                    orderId?.let {
                        if (it != 0L) {
                            Toast.makeText(this@NeedlerMainActivity,
                                getString(R.string.order_sent_text), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@NeedlerMainActivity,
                                getString(R.string.error), Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }

            changeInfoLink.setOnClickListener { _ ->
                createChangeView(this)
            }
        }
    }

    fun createChangeView(binding: ActivityNeedlerMainBinding){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.change_info_layout, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)

        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.change_btn).setOnClickListener {
            alertDialog.dismiss()
            city = dialogView.findViewById<EditText>(R.id.city_name_change_input).text.toString()
            address = dialogView.findViewById<EditText>(R.id.address_change_input).text.toString()

            SharedPreferencesManager.saveDataFromObject(
                prefs,
                CreateOrderDto(
                    city!!,
                    "",
                    "",
                    "",
                    address!!
                ),
                getString(R.string.orderdata_key)
            )

            with(binding) {
                cityNameTextView.text = city
                addressTextView.text = address
            }
        }
    }
}