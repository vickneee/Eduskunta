package com.example.eduskunta

import android.app.Application
import com.example.eduskunta.data.db.AppDatabase
import com.example.eduskunta.repositories.OfflineMemberRepository
import com.example.eduskunta.repositories.OfflineNoteRepository

/**
 * Application class for the Eduskunta app.
 * It initializes the database and the repository.
 * @property database The database instance.
 * @property memberRepository The repository for the members.
 * @property noteRepository The repository for the notes.
 */
class EduskuntaApplication : Application() {

    /**
     * Database instance.
     */
    val database by lazy { AppDatabase.getDatabase(this) }

    /**
     * Repository instance.
     */
    val memberRepository by lazy { OfflineMemberRepository(database.memberDao()) }

    /**
     * Note repository instance.
     */
    val noteRepository by lazy { OfflineNoteRepository(database.noteDao()) }
}
