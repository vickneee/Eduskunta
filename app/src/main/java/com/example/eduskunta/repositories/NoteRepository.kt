package com.example.eduskunta.repositories

import com.example.eduskunta.data.db.entities.NoteEntity
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
     * @param personNumber The person number of the member.
     */
    fun getNotesForMember(personNumber: Int): Flow<List<NoteEntity>>

    /**
     * Add a note to the database.
     * @param note The note to add.
     */
    suspend fun addNote(note: NoteEntity)

    /**
     * Delete a note from the database.
     * @param note The note to delete.
     * For future use.
     */
    suspend fun deleteNote(note: NoteEntity)
}
