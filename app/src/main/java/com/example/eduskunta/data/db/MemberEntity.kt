package com.example.eduskunta.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a member of the Finnish parliament party.
 * @property personNumber The unique identifier of the member.
 * @property seatNumber The seat number of the member.
 * @property last The last name of the member.
 * @property first The first name of the member.
 * @property party The party of the member.
 * @property minister Whether the member is a minister.
 * @property picture The URL of the member's picture.
 * @property twitter The Twitter handle of the member.
 * @property bornYear The year the member was born.
 * @property constituency The constituency of the member.
 */
@Entity(tableName = "members")
data class MemberEntity(
    @PrimaryKey
    val personNumber: Int,
    val seatNumber: Int,
    val last: String,
    val first: String,
    val party: String,
    val minister: Boolean,
    val picture: String,
    val twitter: String,
    val bornYear: Int,
    val constituency: String
)
