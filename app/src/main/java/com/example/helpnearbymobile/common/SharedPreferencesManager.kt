package com.example.helpnearbymobile.common

import android.content.SharedPreferences
import com.example.helpnearbymobile.domain.model.CreateOrderDto
import com.google.gson.Gson

object SharedPreferencesManager {
    fun getOrderDataFromJson(prefs: SharedPreferences, key: String): CreateOrderDto{
        val gson = Gson()
        val json = prefs.getString(key, "")
        return gson.fromJson(json, CreateOrderDto::class.java)
    }

    fun saveDataFromObject(prefs: SharedPreferences, myObject: Any, key: String) {
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(myObject)
        editor.putString(key, json)
        editor.apply()
    }

    fun saveToken(prefs: SharedPreferences, token: String){
        prefs.edit().putString("fbToken", token).apply()
    }
}