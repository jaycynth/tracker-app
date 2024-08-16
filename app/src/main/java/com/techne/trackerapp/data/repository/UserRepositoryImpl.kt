package com.techne.trackerapp.data.repository

import com.techne.trackerapp.data.User
import com.techne.trackerapp.data.UserDao
import com.techne.trackerapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
) : UserRepository {
    override suspend fun insertUser(user: User) = userDao.insertUser(user)

    override suspend fun updateUser(user: User) = userDao.updateUser(user)

    override fun getUsers(): Flow<List<User>> = userDao.getUsers()

    override fun getLastUserRecord(): Flow<User>  = userDao.getLastUserRecord()
}