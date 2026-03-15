package com.example.eduskunta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.eduskunta.ui.theme.EduskuntaTheme

/**
 * Main activity for the Eduskunta app.
 * @constructor Creates a MainActivity instance.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EduskuntaTheme {
                Navigation()
            }
        }
    }
}
