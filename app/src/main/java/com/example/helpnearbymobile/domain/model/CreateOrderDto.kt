package com.example.helpnearbymobile.domain.model

data class CreateOrderDto(
    val cityName: String,
    val commentary: String,
    val status: String,
    val geoLocation: String,
    val address: String
)
