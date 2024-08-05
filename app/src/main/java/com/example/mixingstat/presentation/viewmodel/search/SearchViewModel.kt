package com.example.mixingstat.presentation.viewmodel.search

import android.util.Log
import androidx.lifecycle.LiveData
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


@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: CocktailRepository) :
    ViewModel() {
    val query = MutableLiveData<String>()
    val method = MutableLiveData<String>()
    private val _searchResults = MutableStateFlow<List<Cocktail>>(emptyList())
    val searchResults: StateFlow<List<Cocktail>> = _searchResults


    fun performSearch() {
        viewModelScope.launch {
            val formattedQuery = query.value?.replace(" ", "_") ?: ""
            val searchMethod = method.value
            Log.i("searchMethod",searchMethod!!)
            when (method.value) {
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