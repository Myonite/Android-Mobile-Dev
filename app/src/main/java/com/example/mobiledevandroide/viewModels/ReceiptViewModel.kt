package com.example.mobiledevandroide.viewModels

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mobiledevandroide.data.model.ProcessRequestModel
import com.example.mobiledevandroide.data.model.ReceiptModel
import com.example.mobiledevandroide.data.repository.ReceiptRepository
import com.example.mobiledevandroide.data.source.ReceiptPagingSource
import com.example.mobiledevandroide.network.NetworkClient
import com.example.mobiledevandroide.shared.enums.RefreshState
import com.example.mobiledevandroide.store.SharedPreferencesManager
import com.example.mobiledevandroide.utils.getCurrentDatabaseDateString
import com.example.mobiledevandroide.utils.getFileFromUri
import com.example.mobiledevandroide.utils.isValidDate
import com.example.mobiledevandroide.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject

@HiltViewModel
class ReceiptViewModel @Inject constructor(
    application: Application,
    private val sharedPreferencesManager: SharedPreferencesManager
) : AndroidViewModel(application) {

    private val repository: ReceiptRepository = ReceiptRepository(NetworkClient.apiService)

    private var isRefreshing = false

    private val jwtToken: String
        get() = sharedPreferencesManager.getJwtToken()

    val refreshState = mutableStateOf(RefreshState.INITIAL)
    private val _refreshTrigger = MutableStateFlow(Unit)

    val receipts: Flow<PagingData<ReceiptModel>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { ReceiptPagingSource(repository, jwtToken) },
    ).flow


    fun refreshReceipts() {
        _refreshTrigger.value = Unit
    }

    fun getReceipts() {
        if (!isRefreshing) {
            viewModelScope.launch {
                try {
                    isRefreshing = true
                    refreshState.value = RefreshState.LOADING_MORE
                    receipts.firstOrNull()
                    refreshState.value = RefreshState.SUCCESS
                } catch (e: Exception) {
                    refreshState.value = RefreshState.ERROR
                    e.printStackTrace()
                } finally {
                    isRefreshing = false
                }
            }
        }
    }

    fun uploadAndProcessFile(fileUri: Uri) {
        viewModelScope.launch {
            try {
                val context = getApplication<Application>().applicationContext
                refreshState.value = RefreshState.UPLOADING_FILE
                val file = getFileFromUri(fileUri, context)!!
                val requestFile: RequestBody =
                    file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("receiptImage", file.name, requestFile)

                val responseUpload = repository.uploadFile(body, jwtToken)
                val data = responseUpload.body()?.data
                val processBody =
                    ProcessRequestModel(
                        currency = data?.currency ?: "€",
                        totalAmount = data!!.totalAmount,
                        paymentType = data.paymentType,
                        date = if (isValidDate(data.date)) {
                            data.date
                        } else {
                            getCurrentDatabaseDateString()
                        },
                        receiptImageId = data.receiptImageId
                    )
                val response = repository.processReceipt(jwtToken, processBody)
                if (response.isSuccessful) {
                    showToast(context, "Uploading the receipt was successful!")
                } else {
                    showToast(context, "Failed to upload the receipt!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                refreshState.value = RefreshState.ERROR
            }
        }
    }
}




