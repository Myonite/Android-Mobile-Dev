package com.example.mixingstat.utils

import java.io.IOException


    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T,
        dbCall: suspend () -> T
    ): T {
        return try {
            apiCall()
        } catch (e: IOException) {
            dbCall()
        }
    }
