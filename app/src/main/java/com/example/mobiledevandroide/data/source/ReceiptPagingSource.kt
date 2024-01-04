package com.example.mobiledevandroide.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mobiledevandroide.data.model.ReceiptModel
import com.example.mobiledevandroide.data.repository.ReceiptRepository
import com.example.mobiledevandroide.shared.enums.RefreshState

class ReceiptPagingSource(
    private val repository: ReceiptRepository,
    private val jwtToken: String,
) : PagingSource<Int, ReceiptModel>() {

    private var refreshState: RefreshState = RefreshState.INITIAL

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReceiptModel> {
        return try {
            refreshState = RefreshState.LOADING_MORE

            val currentPage = params.key ?: 1
            val receipts = repository.getReceipts(jwtToken, currentPage).body()?.data?.data
            Log.d("PagingSource", receipts.toString())

            refreshState = if (receipts?.isNotEmpty() == true) RefreshState.SUCCESS else RefreshState.ERROR

            LoadResult.Page(
                data = receipts ?: emptyList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (receipts?.isNotEmpty() == true) currentPage + 1 else null,
            )
        } catch (e: Exception) {
            refreshState = RefreshState.ERROR

            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ReceiptModel>): Int? {
        return state.anchorPosition
    }
}
