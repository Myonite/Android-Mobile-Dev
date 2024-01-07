package com.example.mobiledevandroide.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiledevandroide.store.SharedPreferencesManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    application: Application,
    private val sharedPreferencesManager: SharedPreferencesManager
) : AndroidViewModel(application) {


    fun logout(onLogoutComplete: () -> Unit) {
        viewModelScope.launch {
            sharedPreferencesManager.removeString("jwt_token")
            onLogoutComplete()
        }
    }
}
