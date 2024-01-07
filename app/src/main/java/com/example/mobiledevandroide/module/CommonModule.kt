package com.example.mobiledevandroide.module

import android.content.Context
import android.util.Base64
import com.example.mobiledevandroide.store.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject

@Module
@InstallIn(ViewModelComponent::class)
object SharedPreferencesModule {

    @Provides
    fun provideSharedPreferencesManager(@ApplicationContext context: Context): SharedPreferencesManager {
        return SharedPreferencesManager.getInstance(context)
    }

    @Provides
    fun provideSaveJwtToken(sharedPreferencesManager: SharedPreferencesManager): (String) -> Unit {
        return { token -> sharedPreferencesManager.saveString("jwt_token", token) }
    }

    @Provides
    fun provideGetJwtToken(sharedPreferencesManager: SharedPreferencesManager): () -> String {
        return { sharedPreferencesManager.getString("jwt_token", "") }
    }

    @Provides
    fun provideHasValidJwtToken(sharedPreferencesManager: SharedPreferencesManager): () -> Boolean {
        return {
            val jwtToken = sharedPreferencesManager.getString("jwt_token", "")
            val payloadMap = decodeJwtPayload(jwtToken)
            val expiration = payloadMap["exp"]?.toLongOrNull()
            expiration != null && System.currentTimeMillis() / 1000 < expiration
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

}
