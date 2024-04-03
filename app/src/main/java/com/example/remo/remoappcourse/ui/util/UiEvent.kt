package com.example.remo.remoappcourse.ui.util

sealed interface UiEvent {
    data class Navigate(val route :String) : UiEvent
    object NavigateBack : UiEvent
}