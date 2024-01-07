package com.example.mobiledevandroide.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mobiledevandroide.store.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val sharedPreferencesManager: SharedPreferencesManager
) : AndroidViewModel(application) {


    fun checkToken(): Boolean {
        return sharedPreferencesManager.hasValidJwtToken()
    }
}