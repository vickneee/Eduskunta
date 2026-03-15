package com.example.eduskunta.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.eduskunta.data.db.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the note entity.
 * @property addNote Adds a note to the database.
 * @property getNotesForMember Gets all notes for a member from the database.
 */
@Dao
interface NoteDao {

    /**
     * Get all notes from the database.
     * Flow is used to get updates on the data.
     */
    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NoteEntity>>

    /**
     * Add a note to the database.
     * @param note The note to add.
     */
    @Insert
    suspend fun addNote(note: NoteEntity)

    /**
     * Get all notes for a member from the database.
     * Flow is used to get updates on the data.
     * @param personNumber The person number of the member.
     */
    @Query("SELECT * FROM notes WHERE personNumber = :personNumber")
    fun getNotesForMember(personNumber: Int): Flow<List<NoteEntity>>

    /**
     * Delete a note from the database.
     * @param note The note to delete.
     * For future use.
     */
    @Delete
    suspend fun deleteNote(note: NoteEntity)
}