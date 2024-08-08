package com.example.mixingstat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.mixingstat.ui.theme.MixingStatTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity of the application.
 *
 * This activity is the entry point of the application. It sets the content view to the MixingStatTheme composable function,
 * which applies the MixingStat theme to its content. The content of the MixingStatTheme is a Surface composable that contains
 * the MixingStatApp composable.
 *
 * This activity is annotated with @AndroidEntryPoint, which means that Hilt should inject its dependencies.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting.
     *
     * This method sets the content view of the activity to the MixingStatTheme composable function.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle
     * contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Apply the MixingStat theme to the content.
            MixingStatTheme {
                // The content is a Surface composable that contains the MixingStatApp composable.
                Surface {
                    MixingStatApp()
                }
            }
        }
    }
}