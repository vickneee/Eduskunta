package com.example.eduskunta.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MemberEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao

    /**
     * Singleton instance of the database.
     */
    companion object {
        /**
         * Volatile ensures that the instance is always up-to-date and visible to all threads.
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "eduskunta_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
