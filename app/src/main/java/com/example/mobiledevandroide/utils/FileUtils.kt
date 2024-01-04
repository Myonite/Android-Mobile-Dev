package com.example.mobiledevandroide.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import java.io.File

fun getFileFromUri(uri: Uri, context: Context): File? {
    var filePath: String? = null
    try {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            it.moveToFirst()
            val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
            filePath = it.getString(columnIndex)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return filePath?.let { File(it) }
}
