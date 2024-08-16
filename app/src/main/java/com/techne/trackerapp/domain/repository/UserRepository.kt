package com.techne.trackerapp.domain.repository

import com.techne.trackerapp.data.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insertUser(user: User)

    suspend fun updateUser(user: User)

    fun getUsers():Flow<List<User>>

    fun getLastUserRecord():Flow<User>
}