package com.example.eduskunta.repository

import com.example.eduskunta.data.db.NoteDao
import com.example.eduskunta.data.db.NoteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the note entity.
 */
class OfflineNoteRepository(private val noteDao: NoteDao) : NoteRepository {

    /**
     * Get all notes from the database.
     */
    override fun getAllNotesStream(): Flow<List<NoteEntity>> = noteDao.getAllNotes()

    /**
     * Get notes for a member from the database.
     */
    override fun getNotesForMember(personNumber: Int): Flow<List<NoteEntity>> = noteDao.getNotesForMember(personNumber)

    /**
     * Add a note to the database.
     */
    override suspend fun addNote(note: NoteEntity) = noteDao.addNote(note)

    /**
     * Delete a note from the database.
     * For future use.
     */
    override suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)
}
