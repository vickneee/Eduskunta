package com.example.eduskunta.data.repository

import com.example.eduskunta.data.api.EduskuntaApiService
import com.example.eduskunta.data.db.MemberDao
import com.example.eduskunta.data.db.MemberEntity
import kotlinx.coroutines.flow.Flow

class MemberRepository(
    private val memberDao: MemberDao,
    private val apiService: EduskuntaApiService
) {
    val allMembers: Flow<List<MemberEntity>> = memberDao.getAllMembers()

    suspend fun refreshMembers() {
        try {
            val members = apiService.getEdustajat()
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

    fun getMembersByParty(party: String): Flow<List<MemberEntity>> {
        return memberDao.getMembersByParty(party)
    }

    fun getMember(personNumber: Int): Flow<MemberEntity?> {
        return memberDao.getMember(personNumber)
    }
}
