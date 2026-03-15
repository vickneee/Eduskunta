package com.example.eduskunta.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.eduskunta.data.db.MemberEntity
import com.example.eduskunta.data.repository.MemberRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * View model for the Eduskunta app.
 * @param repository The repository to use for the view model.
 */
class EduskuntaViewModel(private val repository: MemberRepository) : ViewModel() {

    val members: StateFlow<List<MemberEntity>> = repository.allMembers.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    /**
     * Background Refresh of the data.
     */
    init {
        refreshData()
    }

    /**
     * Refresh the data from the API.
     */
    private fun refreshData() {
        viewModelScope.launch {
            try {
                repository.refreshMembers()
            } catch (e: Exception) {
                Log.e("EduskuntaViewModel", "Error refreshing members: ${e.message}")
            }
        }
    }

    /**
     * Factory for creating [EduskuntaViewModel].
     * @param repository The repository to use for the view model.
     */
    companion object {
        fun provideFactory(repository: MemberRepository): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return EduskuntaViewModel(repository) as T
            }
        }
    }
}
