package com.example.eduskunta.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(members: List<MemberEntity>)

    @Query("SELECT * FROM members")
    fun getAllMembers(): Flow<List<MemberEntity>>

    @Query("SELECT * FROM members WHERE party = :party")
    fun getMembersByParty(party: String): Flow<List<MemberEntity>>

    @Query("SELECT * FROM members WHERE personNumber = :personNumber")
    fun getMember(personNumber: Int): Flow<MemberEntity?>
}
