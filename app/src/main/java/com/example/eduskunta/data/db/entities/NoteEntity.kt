package com.example.eduskunta.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a note.
 * @property id The unique identifier of the note.
 * @property personNumber The person number of the member.
 * @property date The date of the note.
 * @property text The text of the note.
 * @property positive Whether the note is positive or negative.
 */
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val personNumber: Int,
    val date: String,
    val text: String,
    val positive: Boolean
)