package com.example.eduskunta.repositories

import com.example.eduskunta.data.db.entities.MemberEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the member entity.
 */
interface MemberRepository {

    /**
     * Get all members from the database.
     */
    fun getAllMembersStream(): Flow<List<MemberEntity>>

    /**
     * Get member by personNumber from the database.
     * @param personNumber The person number of the member.
     */
    fun getMemberStream(personNumber: Int): Flow<MemberEntity?>

    /**
     * Get members by party from the database.
     * @param party The party to get members for.
     */
    fun getMembersByPartyStream(party: String): Flow<List<MemberEntity>>

    /**
     * Insert all members into the database.
     * @param members The members to insert.
     */
    suspend fun insertAll(members: List<MemberEntity>)

    /**
     * Refresh the members from the API and insert them into the database.
     */
    suspend fun refreshMembers()
}
