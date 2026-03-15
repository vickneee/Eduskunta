package com.example.eduskunta.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduskunta.data.api.EduskuntaApi
import com.example.eduskunta.data.db.Edustaja
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EduskuntaViewModel : ViewModel() {
    private val _edustajat = MutableStateFlow<List<Edustaja>>(emptyList())
    val edustajat: StateFlow<List<Edustaja>> = _edustajat

    init {
        getEdustajat()
    }

    fun getEdustajat() {
        // GlobalScope.launch launches a new coroutine in the global
        viewModelScope.launch {
            try {
                val edustajatResult = EduskuntaApi.RETROFIT_SERVICE.getEdustajat()
                Log.d("EduskuntaViewModel", "Edustajat: $edustajatResult")
                Log.d("EduskuntaViewModel", "Edustajat: ${edustajatResult.size}")
                /**
                 * Edustajat list for mapping it to UI
                 */
                _edustajat.value = edustajatResult.map { it }
            } catch (e: Exception) {
                Log.e("EduskuntaViewModel", "Error fetching edustajat: ${e.message}")
            }
        }
    }
}