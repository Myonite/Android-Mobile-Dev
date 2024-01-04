package com.example.mobiledevandroide.store

import android.util.Base64
import org.json.JSONObject

class JwtManager private constructor(private val sharedPreferencesManager: SharedPreferencesManager) {

    fun saveJwtToken(token: String) {
        sharedPreferencesManager.saveString("jwt_token", token)
    }

    fun getJwtToken(): String {
        return sharedPreferencesManager.getString("jwt_token", "")
    }

    fun hasValidJwtToken(): Boolean {
        val jwtToken = getJwtToken()
        val payloadMap = decodeJwtPayload(jwtToken)
        val expiration = payloadMap["exp"]?.toLongOrNull()
        return if (expiration != null && System.currentTimeMillis() / 1000 < expiration){
            true
        } else{

            sharedPreferencesManager.removeString("jwt_token")
            false
        }
    }

    fun getUserId(): Int? {
        val jwtToken = getJwtToken()
        val payloadMap = decodeJwtPayload(jwtToken)
        return payloadMap["exp"]?.toIntOrNull()
    }

    companion object {
        @Volatile
        private var instance: JwtManager? = null

        fun getInstance(sharedPreferencesManager: SharedPreferencesManager): JwtManager {
            return instance ?: synchronized(this) {
                instance ?: JwtManager(sharedPreferencesManager).also { instance = it }
            }
        }
    }
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
