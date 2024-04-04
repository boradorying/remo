package com.example.remo.remoappcourse.ui.screen.memo

sealed interface MemoEvent {
    data class TitleChange(val value:String):MemoEvent
    data class ContentChagne(val value:String):MemoEvent
    object  Save : MemoEvent
    object  NavigateBack : MemoEvent
    object DeleteMemo : MemoEvent

}