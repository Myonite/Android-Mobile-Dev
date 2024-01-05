package com.example.mobiledevandroide.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mobiledevandroide.network.ApiService
import com.example.mobiledevandroide.network.NetworkClient
import com.example.mobiledevandroide.store.JwtManager
import com.example.mobiledevandroide.store.SharedPreferencesManager
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application,
    private val apiService: ApiService = NetworkClient.apiService,
    private val jwtManager: JwtManager,
) : AndroidViewModel(application) {

    private val _loginResult = MutableLiveData<LoginResult>()

    val loginResult: LiveData<LoginResult> get() = _loginResult

    fun login(username: String, password: String, snackbarCallback: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.login(username, password)
                if (response.isSuccessful) {
                    snackbarCallback("Login Successful")
                    _loginResult.value = LoginResult.Success("Success")
                } else {
                    snackbarCallback("Login Unsuccessful, please try again")
                    _loginResult.value = LoginResult.Error("Error")
                }

                response.body()?.let { setJwtToken(it.data) }

            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error logging in: $e")
                _loginResult.value = LoginResult.Error("Error")
            }
        }
    }

    private fun setJwtToken(jwtToken: String) {
        jwtManager.saveJwtToken(jwtToken)
    }
}

sealed class LoginResult {
    data class Success(val message: String) : LoginResult()
    data class Error(val message: String) : LoginResult()
}
