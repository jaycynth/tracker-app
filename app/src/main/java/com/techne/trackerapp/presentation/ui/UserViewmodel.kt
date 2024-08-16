package com.techne.trackerapp.presentation.ui

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techne.trackerapp.data.User
import com.techne.trackerapp.domain.utils.calculateTotalAmount
import com.techne.trackerapp.domain.repository.UserRepository
import com.techne.trackerapp.domain.utils.getTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {

    var state by mutableStateOf(UserState())

    var amountError by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            repository.getUsers().collectLatest { users ->
                state = state.copy(users = users)
            }
        }
    }

    fun validateAmount(amount: String): Boolean {
        val amount = amount.trim()
        var isValid = true
        var errorMessage = ""
        if (amount.isBlank() || amount.isEmpty()) {
            errorMessage = "Please fill amount field"
            isValid = false
        }
        amountError = errorMessage
        return isValid
    }

    private suspend fun getLastRecord() = repository.getLastUserRecord().firstOrNull()

    private suspend fun addUser(amount: Double) {
        //Get last total amount from previous record
        var lastAmount = 0.0
        var lastWeek =  0
        val lastRecord  = getLastRecord()

        if(lastRecord  != null){
            lastAmount = lastRecord.totalAmount
            lastWeek = lastRecord.week
        }

        val user = User(
            id = 0,
            amount = amount,
            totalAmount = calculateTotalAmount(lastAmount, amount),
            week = ++lastWeek,
            date = getTime(),
        )
        viewModelScope.launch {
            repository.insertUser(user = user)
        }
    }

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.OnUpdate -> {
                viewModelScope.launch {
                    addUser(event.amount)
                }
            }
        }
    }
}