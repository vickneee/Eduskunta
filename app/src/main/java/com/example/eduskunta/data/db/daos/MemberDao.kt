package com.example.eduskunta.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eduskunta.data.db.entities.MemberEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the member entity.
 */
@Dao
interface MemberDao {

    /**
     * Insert a list of members into
     * the database. If the member already exists, replace it.
     */
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(members: List<MemberEntity>)

    /**
     * Get all members from the database. Flow is used to get updates on the data.
     */
    @Query("SELECT * FROM members")
    fun getAllMembers(): Flow<List<MemberEntity>>

    /**
     * Get members by party from the database. Flow is used to get updates on the data.
     */
    @Query("SELECT * FROM members WHERE party = :party")
    fun getMembersByParty(party: String): Flow<List<MemberEntity>>

    /**
     * Get member by personNumber from the database. Flow is used to get updates on the data.
     */
    @Query("SELECT * FROM members WHERE personNumber = :personNumber")
    fun getMember(personNumber: Int): Flow<MemberEntity?>
}