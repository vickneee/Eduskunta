package com.example.eduskunta

import android.app.Application
import com.example.eduskunta.data.api.EduskuntaApi
import com.example.eduskunta.data.db.AppDatabase
import com.example.eduskunta.repository.MemberRepository

/**
 * Application class for the Eduskunta app.
 * It initializes the database and the repository.
 * @property database The database instance.
 * @property repository The repository instance.
 * @constructor Creates an EduskuntaApplication instance.
 */
class EduskuntaApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { MemberRepository(database.memberDao(), EduskuntaApi.RETROFIT_SERVICE) }
}
