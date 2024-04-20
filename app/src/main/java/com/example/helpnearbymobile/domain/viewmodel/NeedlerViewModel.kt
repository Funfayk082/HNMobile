package com.example.helpnearbymobile.domain.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helpnearbymobile.domain.ApiConfig
import com.example.helpnearbymobile.domain.model.CreateOrderDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NeedlerViewModel: ViewModel(){
    private val _orderId = MutableLiveData<Long>()
    val orderId: LiveData<Long> = _orderId

    fun postOrder(order: CreateOrderDto, context: Context) {
        val client = ApiConfig.getApiOrders().createOrder(order)
        client.enqueue(object: Callback<Int?> {
            override fun onResponse(call: Call<Int?>, response: Response<Int?>) {
                    _orderId.value = response.body()?.toLong()
            }

            override fun onFailure(call: Call<Int?>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}