package com.example.eduskunta

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eduskunta.ui.theme.EduskuntaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EduskuntaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                    ) {
                        MainView(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainView(
    modifier: Modifier = Modifier
) {
    val viewModel: EduskuntaViewModel = viewModel()
    viewModel.getEdustajat()
}

class EduskuntaViewModel : ViewModel() {
    fun getEdustajat() {
        // GlobalScope.launch launches a new coroutine in the global
        viewModelScope.launch {
            val edustajat = EduskuntaApi.RETROFIT_SERVICE.getEdustajat()
            Log.d("EduskuntaViewModel", "Edustajat: $edustajat")
            // Do something with the edustajat list
            Log.d("EduskuntaViewModel", "Edustajat: ${edustajat.size}")
        }
    }
}

@Composable
fun EduskuntaScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Eduskunta",
        modifier = modifier.padding(16.dp)
    )
}

@Preview()
@Composable
fun EduskuntaPreview() {
    EduskuntaTheme {
        EduskuntaScreen()
    }
}