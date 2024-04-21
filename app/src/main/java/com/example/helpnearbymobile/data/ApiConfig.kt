package com.example.helpnearbymobile.data

import com.example.helpnearbymobile.data.repositories.OrderRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiOrders(): OrderRepository {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://hnapi.containers.cloud.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(OrderRepository::class.java)
        }

        fun getApiAnswers() {
            //TODO: Реализовать апи
        }
    }
}