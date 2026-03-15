package com.example.eduskunta.repository

import com.example.eduskunta.data.db.MemberEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the member entity.
 */
interface MemberRepository {
    fun getAllMembersStream(): Flow<List<MemberEntity>>
    fun getMemberStream(personNumber: Int): Flow<MemberEntity?>
    fun getMembersByPartyStream(party: String): Flow<List<MemberEntity>>
    suspend fun insertAll(members: List<MemberEntity>)
    suspend fun refreshMembers()
}
