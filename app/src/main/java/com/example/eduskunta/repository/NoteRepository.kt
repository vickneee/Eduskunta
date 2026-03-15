package com.example.eduskunta.repository

import com.example.eduskunta.data.db.NoteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the note entity.
 */
interface NoteRepository {

    /**
     * Get all notes from the database.
     */
    fun getAllNotesStream(): Flow<List<NoteEntity>>

    /**
     * Get notes for a member from the database.
     */
    fun getNotesForMember(personNumber: Int): Flow<List<NoteEntity>>

    /**
     * Add a note to the database.
     */
    suspend fun addNote(note: NoteEntity)

    /**
     * Delete a note from the database.
     * For future use.
     */
    suspend fun deleteNote(note: NoteEntity)
}
