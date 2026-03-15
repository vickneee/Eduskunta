package com.example.eduskunta.repositories

import com.example.eduskunta.data.api.EduskuntaApi
import com.example.eduskunta.data.db.daos.MemberDao
import com.example.eduskunta.data.db.entities.MemberEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the member entity.
 * @property memberDao The DAO for the member entity.
 */
class OfflineMemberRepository(private val memberDao: MemberDao) : MemberRepository {

    /**
     * Get all members from the database.
     */
    override fun getAllMembersStream(): Flow<List<MemberEntity>> = memberDao.getAllMembers()

    /**
     * Get member by personNumber from the database.
     */
    override fun getMemberStream(personNumber: Int): Flow<MemberEntity?> = memberDao.getMember(personNumber)

    /**
     * Get members by party from the database.
     */
    override fun getMembersByPartyStream(party: String): Flow<List<MemberEntity>> = memberDao.getMembersByParty(party)

    /**
     * Insert all members into the database.
     */
    override suspend fun insertAll(members: List<MemberEntity>) = memberDao.insertAll(members)

    /**
     * Refresh the members from the API and insert them into the database.
     */
    override suspend fun refreshMembers() {
        val members = EduskuntaApi.RETROFIT_SERVICE.getMembers()
        memberDao.insertAll(members)
    }
}
