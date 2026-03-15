package com.example.eduskunta.repository

import com.example.eduskunta.data.api.EduskuntaApiService
import com.example.eduskunta.data.db.MemberDao
import com.example.eduskunta.data.db.MemberEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the member entity.
 * @property memberDao The DAO for the member entity.
 * @property apiService The API service for the member entity.
 */
class MemberRepository(
    private val memberDao: MemberDao,
    private val apiService: EduskuntaApiService
) {
    /**
     * Get all members from the database.
     */
    val allMembers: Flow<List<MemberEntity>> = memberDao.getAllMembers()

    /**
     * Refresh the members from the API and insert them into the database.
     */
    suspend fun refreshMembers() {
        try {
            val members = apiService.getMembers()
            val entities = members.map {
                MemberEntity(
                    personNumber = it.personNumber,
                    seatNumber = it.seatNumber,
                    last = it.last,
                    first = it.first,
                    party = it.party,
                    minister = it.minister,
                    picture = it.picture,
                    twitter = it.twitter,
                    bornYear = it.bornYear,
                    constituency = it.constituency
                )
            }
            memberDao.insertAll(entities)
        } catch (e: Exception) {
            // Handle error or let it propagate
            throw e
        }
    }

    /**
     * Get members by party from the database.
     */
    fun getMembersByParty(party: String): Flow<List<MemberEntity>> {
        return memberDao.getMembersByParty(party)
    }

    /**
     * Get member by personNumber from the database.
     */
    fun getMember(personNumber: Int): Flow<MemberEntity?> {
        return memberDao.getMember(personNumber)
    }
}
