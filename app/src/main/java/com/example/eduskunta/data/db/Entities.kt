package com.example.eduskunta.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// Members Table - All Parliament members saved from API
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
