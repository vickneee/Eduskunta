package com.example.eduskunta

import android.app.Application
import com.example.eduskunta.data.api.EduskuntaApi
import com.example.eduskunta.data.db.AppDatabase
import com.example.eduskunta.data.repository.MemberRepository

class EduskuntaApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { MemberRepository(database.memberDao(), EduskuntaApi.RETROFIT_SERVICE) }
}
