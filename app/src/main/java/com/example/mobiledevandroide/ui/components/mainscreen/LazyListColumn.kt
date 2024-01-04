package com.example.mobiledevandroide.ui.components.mainscreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.mobiledevandroide.data.model.ReceiptModel
import com.example.mobiledevandroide.ui.views.ReceiptItem
import com.example.mobiledevandroide.viewModels.ReceiptViewModel

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun LazyListColumn(
    innerPadding: PaddingValues,
    pullRefreshState: PullRefreshState,
    lazyPagingItems: LazyPagingItems<ReceiptModel>,
    navigateTo: (route: String) -> Unit,
    receiptViewModel: ReceiptViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .pullRefresh(pullRefreshState),
    ) {


        items(lazyPagingItems.itemCount) { index ->
            val receipt = lazyPagingItems[index]
            if (receipt != null) {
                ReceiptItem(receipt) {
                    navigateTo("details/${receipt.id}")
                }

                if (index == lazyPagingItems.itemCount - 1) {
                    receiptViewModel.getReceipts(index)
                }
            }
        }

    }
}