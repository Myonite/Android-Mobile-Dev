package com.example.mixingstat.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

class ApiUtils(private val dispatcher: CoroutineDispatcher) {

    private suspend fun <T> safeApiCall(
        apiCall: suspend () -> T,
        dbCall: suspend () -> T
    ): T {
        return try {
            apiCall()
        } catch (e: IOException) {
            dbCall()
        }
    }

    suspend fun <T> performRequest(
        apiCall: suspend () -> Response<T>,
        dbCall: suspend () -> T?,
        processResponse: suspend (T) -> Unit
    ): T {
        return withContext(dispatcher) {
            safeApiCall(
                apiCall = {
                    val response = apiCall.invoke()
                    val responseBody = response.body()
                    if (responseBody != null) {
                        processResponse(responseBody)
                    }
                    responseBody ?: throw IOException("Response body is null")
                },
                dbCall = { dbCall.invoke() ?: throw IOException("Database call returned null") }
            )
        }
    }
}
