package com.example.mixingstat.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mixingstat.R


object StringUtils {
    @Composable
    fun defaultIfNull(value: String?): String {
        val default = stringResource(R.string.message_default_if_null)
        return value ?: default
    }
}