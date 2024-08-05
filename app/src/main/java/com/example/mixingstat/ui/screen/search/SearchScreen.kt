package com.example.mixingstat.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mixingstat.R
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.presentation.viewmodel.search.SearchViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(searchQuery: String? = null, searchMethod: String? = null, navigateTo: (route: String) -> Unit, viewModel: SearchViewModel = hiltViewModel()) {
    var text by rememberSaveable { mutableStateOf(searchQuery ?: "") }
    var dropdownOpen by remember { mutableStateOf(false) }
    val queryState by viewModel.query.observeAsState()
    val methodState by viewModel.method.observeAsState()

    val searchMethods = listOf(
        stringResource(R.string.cocktail),
        stringResource(R.string.ingredient)
    )
    var selectedSearchMethod by rememberSaveable { mutableStateOf(searchMethod?.lowercase(Locale.getDefault()) ?: searchMethods[0]) }
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
            .semantics { isTraversalGroup = true }) {
        Row {
            SearchBar(
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
                selectedSearchMethod?.substring(0, 2)?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(start = 14.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
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
        SearchResults(searchResults,navigateTo)
    }

}

@Composable
fun SearchResults(searchResults: List<Cocktail>?, navigateTo: (route: String) -> Unit,) {
    if (searchResults.isNullOrEmpty()) {
        Text(
            text = "No results",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize().padding(top = 70.dp)
        )
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(top = 70.dp)
        ) {
            items(searchResults) { cocktail ->
                Card(modifier = Modifier.padding(8.dp).clickable { navigateTo("cocktail/${cocktail.idDrink}") }) {
                    Column {
                        AsyncImage(
                            model = cocktail.strDrinkThumb,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                        Text(
                            text = cocktail.strDrink,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}