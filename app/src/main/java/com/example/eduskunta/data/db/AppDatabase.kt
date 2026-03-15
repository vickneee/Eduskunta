package com.example.eduskunta.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eduskunta.data.db.daos.MemberDao
import com.example.eduskunta.data.db.daos.NoteDao
import com.example.eduskunta.data.db.entities.MemberEntity
import com.example.eduskunta.data.db.entities.NoteEntity

/**
 * Room database for the app.
 * @property AppDatabase Singleton instance of the database.
 */
@Database(
    entities = [MemberEntity::class, NoteEntity::class],
    version = 2,
    exportSchema = false
)

/**
 * Abstract class representing the database.
 * @property getDatabase Returns the database instance.
 * @property AppDatabase Singleton instance of the database.
 * @property memberDao Returns the member DAO.
 * @property noteDao Returns the note DAO.
 */
abstract class AppDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao
    abstract fun noteDao(): NoteDao

    /**
     * Singleton instance of the database.
     */
    companion object {
        /**
         * Volatile ensures that the instance is always up-to-date and visible to all threads.
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Get the database instance. If it doesn't exist, create it.
         * @param context The application context.
         * @return The database instance.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "eduskunta_database"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
