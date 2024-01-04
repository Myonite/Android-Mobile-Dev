package com.example.mobiledevandroide.store

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPreferencesManager private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "Prefs",
        Context.MODE_PRIVATE,
    )

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
        Log.d("SharedPreferencesManager", "Saved: $key - $value")
    }

    fun getString(key: String, defaultValue: String): String {
        val savedValue = sharedPreferences.getString(key, defaultValue) ?: defaultValue
        Log.d("SharedPreferencesManager", "Retrieved: $key - $savedValue")
        return savedValue
    }

    fun removeString(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    companion object {

        @Volatile
        private var instance: SharedPreferencesManager? = null

        fun getInstance(context: Context): SharedPreferencesManager {
            return instance ?: synchronized(this) {
                instance ?: SharedPreferencesManager(context).also { instance = it }
            }
        }
    }
}
