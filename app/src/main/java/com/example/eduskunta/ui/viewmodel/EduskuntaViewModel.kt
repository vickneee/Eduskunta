package com.example.eduskunta.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.eduskunta.data.db.entities.MemberEntity
import com.example.eduskunta.data.db.entities.NoteEntity
import com.example.eduskunta.repositories.MemberRepository
import com.example.eduskunta.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * View model for the Eduskunta app.
 * @param repository The repository to use for the view model.
 * @param noteRepository The repository to use for the notes.
 */
class EduskuntaViewModel(
    private val repository: MemberRepository,
    private val noteRepository: NoteRepository
) : ViewModel() {

    /**
     * State flow of the members.
     * @property members The state flow of the members.
     */
    val members: StateFlow<List<MemberEntity>> = repository.getAllMembersStream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    /**
     * State flow of the notes.
     * @property notes The state flow of the notes.
     */
    val notes: StateFlow<List<NoteEntity>> = noteRepository.getAllNotesStream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    /**
     * Get members by party from the database.
     * @param party The party to get members for.
     */
    fun getMembersByParty(party: String): Flow<List<MemberEntity>> {
        return repository.getMembersByPartyStream(party)
    }

    /**
     * Get member by personNumber from the database.
     * @param personNumber The person number of the member.
     */
    fun getMember(personNumber: Int): Flow<MemberEntity?> {
        return repository.getMemberStream(personNumber)
    }

    /**
     * Get the notes for a member.
     * @param personNumber The person number of the member.
     */
    fun getNotes(personNumber: Int): Flow<List<NoteEntity>> {
        return noteRepository.getNotesForMember(personNumber)
    }

    /**
     * Add a note for a member.
     * @param personNumber The person number of the member.
     * @param text The text of the note.
     * @param positive Whether the note is positive or negative.
     */
    fun addNote(personNumber: Int, text: String, positive: Boolean) {
        viewModelScope.launch {
            noteRepository.addNote(
                NoteEntity(
                    personNumber = personNumber,
                    date = LocalDate.now().toString(),
                    text = text,
                    positive = positive
                )
            )
        }
    }

    /**
     * Background Refresh of the data.
     * @property refreshData The function to refresh the data.
     */
    init {
        refreshData()
    }

    /**
     * Refresh the data from the API.
     * @property viewModelScope The scope of the view model.
     * @property repository The repository to use for the view model.
     * @property refreshData The function to refresh the data.
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
     * @param memberRepository The repository to use for the view model.
     * @param noteRepository The repository to use for the notes.
     */
    companion object {
        fun provideFactory(
            memberRepository: MemberRepository,
            noteRepository: NoteRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return EduskuntaViewModel(memberRepository, noteRepository) as T
            }
        }
    }
}
