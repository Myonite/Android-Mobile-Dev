package com.example.mobiledevandroide.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiledevandroide.store.SharedPreferencesManager
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferencesManager = SharedPreferencesManager.getInstance(application)

    fun logout(onLogoutComplete: () -> Unit) {
        viewModelScope.launch {
            sharedPreferencesManager.removeString("jwt_token")


            onLogoutComplete()
        }
    }
}
