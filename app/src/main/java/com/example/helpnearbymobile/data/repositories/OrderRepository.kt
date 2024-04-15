package com.example.helpnearbymobile.data.repositories

import com.example.helpnearbymobile.domain.model.CreateOrderDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderRepository    {
    @POST("api/order")
    fun createOrder(@Body order: CreateOrderDto): Call<Int>
}