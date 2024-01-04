package com.example.mobiledevandroide.network

import com.example.mobiledevandroide.data.model.DeleteResponse
import com.example.mobiledevandroide.data.model.LoginResponseModel
import com.example.mobiledevandroide.data.model.ProcessRequestModel
import com.example.mobiledevandroide.data.model.ProcessResponseModel
import com.example.mobiledevandroide.data.model.ReceiptDetailResponse
import com.example.mobiledevandroide.data.model.ReceiptImageResponse
import com.example.mobiledevandroide.data.model.ReceiptModel
import com.example.mobiledevandroide.data.model.ReceiptSaveModel
import com.example.mobiledevandroide.data.model.ReceiptsResponse
import com.example.mobiledevandroide.data.model.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("/api/auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Response<LoginResponseModel>

    @GET("/api/receipts")
    suspend fun getAllReceiptsUser(
        @Header("Authorization") authorization: String,
        @Query("sort[sortBy]") sortBy: String,
        @Query("page") page: Int,
    ): Response<ReceiptsResponse>

    @GET("/api/receipts/{receiptId}")
    suspend fun getReceiptUser(
        @Header("Authorization") authorization: String,
        @Path("receiptId") receiptId: String,
    ): Response<ReceiptDetailResponse>

    @PUT("/api/receipts/{receiptId}")
    suspend fun saveReceiptDetail(
        @Header("Authorization") authorization: String,
        @Body receiptModel: ReceiptModel,
        @Path("receiptId") receiptId: String,
        @Header("Content-Type") contentType: String = "application/json",
    ): Response<ReceiptSaveModel>

    @GET("/api/receipts/{receiptImageId}/image")
    suspend fun getReceiptImage(
        @Header("Authorization") authorization: String,
        @Path("receiptImageId") receiptImageId: Int,
    ): Response<ReceiptImageResponse>

    @Multipart
    @POST("/api/receipts/upload")
    suspend fun uploadFile(
        @Header("Authorization") authorization: String,
        @Part file: MultipartBody.Part
    ): Response<UploadResponse>


    @POST("/api/receipts/process")
    suspend fun processFile(
        @Header("Authorization") authorization: String,
        @Body receipt: ProcessRequestModel,
    ): Response<ProcessResponseModel>

    @DELETE("/api/receipts/{receiptId}")
    suspend fun deleteReceipt(
        @Header("Authorization") authorization: String,
        @Path("receiptId") receiptId: String,
    ): Response<DeleteResponse>



}
