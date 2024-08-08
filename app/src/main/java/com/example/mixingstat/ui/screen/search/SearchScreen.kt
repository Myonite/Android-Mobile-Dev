package com.example.mixingstat.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mixingstat.R
import com.example.mixingstat.presentation.viewmodel.search.SearchViewModel
import java.util.Locale


/**
 * Composable function that displays a random drink screen.
 *
 * This function displays a question mark icon that rotates back and forth while the random drink is being loaded.
 * Once the random drink is loaded, it displays the details of the drink using the CocktailDetailScreen composable.
 * If the random drink cannot be loaded, it displays an error message.
 *
 * @param navigateTo A function that takes a route as a parameter and navigates to the corresponding screen.
 * @param viewModel The ViewModel that provides the state for the RandomDrinkScreen. By default, it uses the RandomDrinkViewModel provided by Hilt.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchQuery: String? = null,
    searchMethod: String? = null,
    navigateTo: (route: String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var text by rememberSaveable { mutableStateOf(searchQuery ?: "") }
    var dropdownOpen by remember { mutableStateOf(false) }
    val queryState by viewModel.query.observeAsState("")
    val methodState by viewModel.method.observeAsState("")
    val scrollState = rememberScrollState()


    val searchMethods = listOf(
        stringResource(R.string.cocktail),
        stringResource(R.string.ingredient)
    )
    var selectedSearchMethod by rememberSaveable {
        mutableStateOf(
            searchMethod?.lowercase(Locale.getDefault()) ?: searchMethods[0]
        )
    }
    LaunchedEffect(Unit) {
        queryState?.let {
            text = it
        }
        methodState?.let {
            selectedSearchMethod = it.lowercase(Locale.getDefault())
        }
    }
    LaunchedEffect(selectedSearchMethod) {
        viewModel.method.value = selectedSearchMethod
        viewModel.query.value = text
    }

    LaunchedEffect(searchQuery, selectedSearchMethod) {
        viewModel.performSearch()
    }

    val searchResults by viewModel.searchResults.collectAsState(initial = emptyList())
    Box(
        Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true })
    Row(Modifier.offset(y = if (scrollState.value > 50) (-50).dp else -scrollState.value.dp)) {
        SearchBar(
            modifier = Modifier
                .weight(0.9f),
            query = text,
            onQueryChange = {
                text = it
                viewModel.query.value = it
            },
            onSearch = {
                viewModel.performSearch()
            },
            active = false,
            onActiveChange = { },
            placeholder = {
                Text(stringResource(R.string.search_lable))
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable {
                            text = ""
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }

            },
            content = {}
        )
        Column {

            IconButton(onClick = { dropdownOpen = true }) {
                Icon(imageVector = Icons.Default.FilterList, contentDescription = null)
            }
            Text(
                text = selectedSearchMethod.substring(0, 2),
                modifier = Modifier.padding(start = 14.dp)
            )
        }

        Box(
            modifier = Modifier
                .weight(0.1f)
                .wrapContentSize(Alignment.TopStart)
                .offset(y = 69.dp)
        ) {
            DropdownMenu(
                expanded = dropdownOpen,
                onDismissRequest = { dropdownOpen = false },
                modifier = Modifier.width(90.dp)
            ) {
                searchMethods.forEach { method ->
                    DropdownMenuItem(onClick = {
                        selectedSearchMethod = method.lowercase(Locale.getDefault())
                        dropdownOpen = false
                        viewModel.method.value = selectedSearchMethod
                    }, text = { Text(method) })
                }
            }
        }
    }
    SearchResults(searchResults, navigateTo)
}


