package com.example.mixingstat.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

/**
 * Composable function that applies a given SVG color to its content.
 *
 * This function provides the given SVG color to its content through the LocalSvgColor composition local.
 *
 * @param svgColor The SVG color to be applied to the content.
 * @param content The content to which the SVG color should be applied.
 */
class ApiUtils(private val dispatcher: CoroutineDispatcher) {

    /**
     * Safely performs an API call and a database call.
     *
     * This method tries to perform the API call first. If the API call fails with an IOException, it performs the database call.
     *
     * @param apiCall The suspend function that performs the API call.
     * @param dbCall The suspend function that performs the database call.
     * @return The result of the API call if it is successful, otherwise the result of the database call.
     */
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

    /**
     * Performs a request and processes the response.
     *
     * This method performs an API call and processes the response. If the API call fails, it performs a database call.
     * The response of the API call is processed by the processResponse function.
     *
     * @param apiCall The suspend function that performs the API call.
     * @param dbCall The suspend function that performs the database call.
     * @param processResponse The suspend function that processes the response of the API call.
     * @return The result of the API call if it is successful, otherwise the result of the database call.
     */
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
