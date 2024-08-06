package com.example.mixingstat.presentation.viewmodel.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.data.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Search screen. This ViewModel is responsible for managing the state of the Search screen and performing search operations through the repository.
 *
 * @property repository The repository from which cocktails are searched.
 * @property query The mutable live data that represents the search query.
 * @property method The mutable live data that represents the search method.
 * @property _searchResults The mutable state flow that represents the search results.
 * @property searchResults The state flow that represents the search results. This is what UI components should observe.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: CocktailRepository) :
    ViewModel() {
    val query = MutableLiveData<String>()
    val method = MutableLiveData<String>()
    private val _searchResults = MutableStateFlow<List<Cocktail>>(emptyList())
    val searchResults: StateFlow<List<Cocktail>> = _searchResults

    /**
     * Performs a search operation based on the current query and method. The search operation is performed asynchronously in the ViewModel scope.
     * The search method can be either "cocktail" or "ingredient". If it's "cocktail", the search is performed by the first letter if the query length is 1, otherwise by name.
     * If the search method is "ingredient", the search is performed by ingredient name.
     * The results of the search operation are stored in _searchResults.
     */
    fun performSearch() {
        viewModelScope.launch {
            val formattedQuery = query.value?.replace(" ", "_") ?: ""
            val searchMethod = method.value?.lowercase()
            Log.i("searchMethod",searchMethod!!)
            when (searchMethod) {
                "cocktail" -> {
                    if (formattedQuery.length == 1) {
                        val response = repository.searchCocktailsByFirstLetter(formattedQuery.first())
                        _searchResults.value = response!!
                    } else {
                        val response = repository.searchCocktailByName(formattedQuery)
                        _searchResults.value = response!!
                    }
                }
                "ingredient" -> {
                    val response = repository.searchByIngredientName(formattedQuery)
                    _searchResults.value = response!!
                }
            }
        }
    }
}