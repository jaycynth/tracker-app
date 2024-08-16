package com.techne.trackerapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val totalAmount: Double,
    val amount:Double,
    val week: Int,
    val date:String
)
