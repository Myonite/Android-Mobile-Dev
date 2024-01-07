package com.example.mobiledevandroide.store

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import org.json.JSONObject

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

    fun saveJwtToken(token: String) {
        saveString("jwt_token", token)
    }

    fun getJwtToken(): String {
        return getString("jwt_token", "")
    }

    fun hasValidJwtToken(): Boolean {
        val jwtToken = getJwtToken()
        val payloadMap = decodeJwtPayload(jwtToken)
        val expiration = payloadMap["exp"]?.toLongOrNull()
        return expiration != null && System.currentTimeMillis() / 1000 < expiration
    }

    fun decodeJwtPayload(jwt: String): Map<String, String> {
        val parts = jwt.split(".")
        if (parts.size < 2) {
            return emptyMap()
        }

        val encodedPayload = parts[1]
        val decodedPayload = String(Base64.decode(encodedPayload, Base64.URL_SAFE), Charsets.UTF_8)

        return try {
            val json = JSONObject(decodedPayload)
            val payloadMap = mutableMapOf<String, String>()

            payloadMap["iat"] = json.optString("iat", "")
            payloadMap["userId"] = json.optString("userId", "")
            payloadMap["exp"] = json.optString("exp", "")

            payloadMap
        } catch (e: Exception) {
            emptyMap()
        }
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
