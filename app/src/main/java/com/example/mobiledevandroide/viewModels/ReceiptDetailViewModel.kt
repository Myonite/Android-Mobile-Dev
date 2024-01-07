package com.example.mobiledevandroide.viewModels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiledevandroide.data.dao.ReceiptDao
import com.example.mobiledevandroide.data.entity.toReceiptModel
import com.example.mobiledevandroide.store.SharedPreferencesManager
import com.example.mobiledevandroide.data.model.ReceiptModel
import com.example.mobiledevandroide.data.repository.LocalReceiptsRepo
import com.example.mobiledevandroide.data.repository.ReceiptRepository
import com.example.mobiledevandroide.network.NetworkClient
import com.example.mobiledevandroide.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class ReceiptDetailViewModel @Inject constructor(
    application: Application,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val localReceiptsRepo: LocalReceiptsRepo
) : AndroidViewModel(application) {

    private val repository: ReceiptRepository = ReceiptRepository(NetworkClient.apiService)


    private val _receiptModelDetail = MutableStateFlow<ReceiptModel?>(null)
    val receiptModelDetail: StateFlow<ReceiptModel?> = _receiptModelDetail

    private val jwtToken: String
        get() = sharedPreferencesManager.getJwtToken()

    fun loadReceiptDetail(receiptId: String) {
        viewModelScope.launch {
            try {
                val receiptDetail = repository.getReceiptDetail(jwtToken, receiptId)
                val receiptImage = repository.getReceiptImage(
                    jwtToken,
                    receiptDetail.body()?.data!!.receiptImageId
                )
                if (receiptDetail.isSuccessful && receiptImage.isSuccessful
                ) {
                    receiptDetail.body()?.data!!.receiptImage =
                        decodeBase64ToBitmap(receiptImage.body()?.data!!)
                    _receiptModelDetail.value = receiptDetail.body()?.data
                } else {
                    handleApiError("Loading receipt")
                }
            } catch (e: Exception) {
                handleException(e)
                try {
                    _receiptModelDetail.value =
                        localReceiptsRepo.getLocalReceipt(receiptId)?.toReceiptModel()
                } catch (f: Exception) {
                    handleException(f)
                }
            }
        }
    }

    private fun formatReceiptForApi(receiptModel: ReceiptModel): ReceiptModel {
        val originalDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        return receiptModel.copy(date = formattedDate.format(originalDateFormat.parse(receiptModel.date)!!))
    }

    fun saveReceiptDetail(receiptModel: ReceiptModel, receiptId: String) {
        viewModelScope.launch {
            try {
                val response = repository.saveReceiptDetail(
                    jwtToken,
                    formatReceiptForApi(receiptModel),
                    receiptId
                )
                if (response.isSuccessful) {
                    showToastHelper("Receipt details saved successfully")
                } else {
                    handleApiError("Receipt details save")
                }
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    fun deleteReceipt(receiptId: String) {
        viewModelScope.launch {
            try {
                val response = repository.deleteReceipt(jwtToken, receiptId)
                if (response.isSuccessful) {
                    showToastHelper("Receipt details deleted successfully")
                } else {
                    handleApiError("Receipt details save")
                }
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }


    private fun handleApiError(message: String) {
        showToastHelper(message + "could not be completed")
        Log.e("ReceiptDetailViewModel", "API error occurred")
    }

    private fun handleException(exception: Exception) {
        showToastHelper("An error occurred")
        Log.e("ReceiptDetailViewModel", exception.toString())
    }

    private fun showToastHelper(message: String) {
        val context = getApplication<Application>().applicationContext
        showToast(context, message)
    }

    private fun decodeBase64ToBitmap(base64String: String): Bitmap {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

    }
}
