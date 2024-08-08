package com.example.mixingstat.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mixingstat.R

/**
 * Object that provides utility functions for handling strings.
 */
object StringUtils {
    /**
     * Composable function that returns the given string if it is not null, otherwise returns a default string.
     *
     * This function checks if the given string is null. If it is null, it returns a default string obtained from the string resources.
     * If the given string is not null, it returns the given string.
     *
     * @param value The string to check. If this string is null, the function returns a default string.
     * @return The given string if it is not null, otherwise a default string.
     */
    @Composable
    fun defaultIfNull(value: String?): String {
        val default = stringResource(R.string.message_default_if_null)
        return value ?: default
    }
}