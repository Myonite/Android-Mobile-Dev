package com.example.mixingstat

import android.app.Application
import android.net.ConnectivityManager
import com.example.mixingstat.config.NetworkStatus
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Application class for the MixingStat app.
 *
 * This class is the base class for maintaining global application state. It is instantiated before any other class when the process for
 * your application/package is created. It is annotated with @HiltAndroidApp, which triggers Hilt's code generation and adds an
 * application-level dependency container.
 *
 * This class also registers a network callback to monitor the network status throughout the app's lifecycle.
 *
 * @property connectivityManager The system service that answers queries about the state of network connectivity.
 */
@HiltAndroidApp
class MixingApp : Application() {
    @Inject
    lateinit var connectivityManager: ConnectivityManager

    /**
     * Called when the application is starting, before any activity, service, or receiver objects (excluding content providers) have been created.
     * It registers a network callback to monitor the network status.
     */
    override fun onCreate() {
        super.onCreate()
        NetworkStatus.registerNetworkCallback(connectivityManager)
    }
}