package com.example.helpnearbymobile.domain.model

data class GetOrderDto(
    val id: Long,
    val cityName: String,
    val commentary: String,
    val status: String,
    val geoLocation: String,
    val address: String
)
