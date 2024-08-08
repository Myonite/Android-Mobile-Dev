package com.example.mixingstat.config

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * This object is used to monitor the network status of the device.
 * It provides a StateFlow which emits the current network status.
 */
object NetworkStatus {
    // A MutableStateFlow that emits the current network status.
    private val _isConnected = MutableStateFlow(false)

    /**
     * A read-only StateFlow that emits the current network status.
     * External consumers should observe this StateFlow to get network status updates.
     */
    val isConnected: StateFlow<Boolean> get() = _isConnected

    /**
     * A callback to listen for network status changes.
     * When the network is available, it sets the _isConnected MutableStateFlow to true.
     * When the network is lost, it sets the _isConnected MutableStateFlow to false.
     */
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isConnected.value = true
        }

        override fun onLost(network: Network) {
            _isConnected.value = false
        }
    }

    /**
     * This function registers the networkCallback with the ConnectivityManager.
     * It should be called when you want to start listening for network status updates.
     *
     * @param connectivityManager The ConnectivityManager to register the callback with.
     */
    fun registerNetworkCallback(connectivityManager: ConnectivityManager) {
        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}