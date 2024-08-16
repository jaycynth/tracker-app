package com.techne.trackerapp.presentation.ui


sealed class UserEvent {
    data class OnUpdate(val amount:Double) : UserEvent()
}