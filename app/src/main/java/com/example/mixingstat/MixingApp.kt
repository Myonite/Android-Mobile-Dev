package com.example.mixingstat

import android.app.Application
import android.net.ConnectivityManager
import com.example.mixingstat.config.NetworkStatus
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MixingApp : Application() {
    @Inject
    lateinit var connectivityManager: ConnectivityManager

    override fun onCreate() {
        super.onCreate()
        NetworkStatus.registerNetworkCallback(connectivityManager)
    }
}