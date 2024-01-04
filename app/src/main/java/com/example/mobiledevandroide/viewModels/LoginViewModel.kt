package com.example.mobiledevandroide.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mobiledevandroide.store.SharedPreferencesManager
import com.example.mobiledevandroide.network.NetworkClient.apiService
import com.example.mobiledevandroide.utils.showToast
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferencesManager = SharedPreferencesManager.getInstance(application)

    private val _loginResult = MutableLiveData<LoginResult>()

    val loginResult: LiveData<LoginResult> get() = _loginResult

    fun login(username: String, password: String) {
        val context = getApplication<Application>().applicationContext
        viewModelScope.launch {
            try {
                val response = apiService.login(username, password)
                if (response.isSuccessful) {
                    showToast(context, "Login Succesful")
                    _loginResult.value = LoginResult.Success("Success")
                } else {
                    showToast(context, "Login Unsuccesful, please try again")
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
        sharedPreferencesManager.saveString("jwt_token", jwtToken)
    }
}

sealed class LoginResult {
    data class Success(val message: String) : LoginResult()
    data class Error(val message: String) : LoginResult()
}