package com.example.mobiledevandroide.data.repository

import com.example.mobiledevandroide.data.model.DeleteResponse
import com.example.mobiledevandroide.data.model.ProcessRequestModel
import com.example.mobiledevandroide.data.model.ProcessResponseModel
import com.example.mobiledevandroide.data.model.ReceiptDetailResponse
import com.example.mobiledevandroide.data.model.ReceiptImageResponse
import com.example.mobiledevandroide.data.model.ReceiptModel
import com.example.mobiledevandroide.data.model.ReceiptSaveModel
import com.example.mobiledevandroide.data.model.ReceiptsResponse
import com.example.mobiledevandroide.data.model.UploadResponse
import com.example.mobiledevandroide.network.ApiService
import okhttp3.MultipartBody
import retrofit2.Response

class ReceiptRepository(
    private val apiService: ApiService,
) {

    private fun getBearerToken(jwtToken: String): String = "Bearer $jwtToken"

    suspend fun getReceipts(jwtToken: String, currentPage: Int): Response<ReceiptsResponse> {
        return apiService.getAllReceiptsUser(getBearerToken(jwtToken), "id", currentPage)

    }

    suspend fun getReceiptDetail(
        jwtToken: String,
        receiptId: String,
    ): Response<ReceiptDetailResponse> {
        return apiService.getReceiptUser(getBearerToken(jwtToken), receiptId)

    }

    suspend fun saveReceiptDetail(
        jwtToken: String,
        receiptModel: ReceiptModel,
        receiptId: String,
    ): Response<ReceiptSaveModel> {
        return apiService.saveReceiptDetail(getBearerToken(jwtToken), receiptModel, receiptId)

    }

    suspend fun uploadFile(
        body: MultipartBody.Part,
        jwtToken: String
    ): Response<UploadResponse> {
        return apiService.uploadFile(getBearerToken(jwtToken), body)

    }


    suspend fun processReceipt(
        jwtToken: String,
        body: ProcessRequestModel
    ): Response<ProcessResponseModel> {
        return apiService.processFile(getBearerToken(jwtToken), body)
    }

    suspend fun deleteReceipt(jwtToken: String, receiptId: String): Response<DeleteResponse> {
        return apiService.deleteReceipt(getBearerToken(jwtToken), receiptId)
    }

    suspend fun getReceiptImage(
        jwtToken: String,
        receiptImageId: Int
    ): Response<ReceiptImageResponse> {
        return apiService.getReceiptImage(getBearerToken(jwtToken), receiptImageId)
    }



}
