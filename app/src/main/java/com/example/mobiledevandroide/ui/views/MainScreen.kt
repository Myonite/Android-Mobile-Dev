package com.example.mobiledevandroide.ui.views

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mobiledevandroide.shared.enums.RefreshState
import com.example.mobiledevandroide.ui.components.mainscreen.LazyListColumn
import com.example.mobiledevandroide.ui.components.scaffold.BottomBar
import com.example.mobiledevandroide.ui.components.scaffold.FloatingButton
import com.example.mobiledevandroide.ui.components.scaffold.TopBar
import com.example.mobiledevandroide.viewModels.ReceiptViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    receiptViewModel: ReceiptViewModel = viewModel(),
    navigateTo: (route: String) -> Unit,
) {
    val lazyPagingItems = receiptViewModel.receipts.collectAsLazyPagingItems()
    val refreshScope = rememberCoroutineScope()
    val state by receiptViewModel.refreshState
    var refreshScreen by remember { mutableStateOf(false) }


    fun refresh() = refreshScope.launch {
        refreshScreen = true
        try {
            receiptViewModel.refreshReceipts()
            lazyPagingItems.refresh()
        } catch (e: Exception) {
            Log.e("MainScreen", "Issue met het ophalen van de receipts")
        } finally {
            refreshScreen = false
        }
    }


    val fileChooser =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {

                try {
                    receiptViewModel.uploadAndProcessFile(uri)
                } catch (e: Exception) {
                    Log.e("MainScreen", "Issue met het ophalen van de receipts")
                }
            }
        }


    val pullRefreshState = rememberPullRefreshState(
        refreshing = (state == RefreshState.REFRESHING),
        onRefresh = ::refresh
    )


    Surface(
        color = MaterialTheme.colorScheme.background,
    ) {
        Scaffold(
            topBar = { TopBar(navController) },
            bottomBar = { BottomBar(navController) },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = { FloatingButton(fileChooser) },
        ) { innerPadding ->
            Box(Modifier.pullRefresh(pullRefreshState)) {

                LazyListColumn(
                    innerPadding,
                    pullRefreshState,
                    lazyPagingItems,
                    navigateTo,
                    receiptViewModel
                )
                PullRefreshIndicator(
                    refreshing = (state == RefreshState.REFRESHING),
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    contentColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                )


            }
        }

    }
}



