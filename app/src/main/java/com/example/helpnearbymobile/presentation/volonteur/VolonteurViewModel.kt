package com.example.helpnearbymobile.presentation.volonteur

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helpnearbymobile.data.ApiConfig
import com.example.helpnearbymobile.domain.model.GetOrderDto
import retrofit2.Callback

class VolonteurViewModel: ViewModel() {
    private val _orders = MutableLiveData<List<GetOrderDto>>()
    val orders: LiveData<List<GetOrderDto>> = _orders

    fun getOrders(cityName: String) {
        val client = ApiConfig.getApiOrders().getOrders(cityName)
        client.enqueue(object: Callback<List<GetOrderDto>>{
            override fun onFailure(call: retrofit2.Call<List<GetOrderDto>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: retrofit2.Call<List<GetOrderDto>>, response: retrofit2.Response<List<GetOrderDto>>) {
                _orders.value = response.body()
            }
        })
    }
}