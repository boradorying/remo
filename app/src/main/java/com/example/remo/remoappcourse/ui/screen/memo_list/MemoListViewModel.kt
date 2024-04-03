package com.example.remo.remoappcourse.ui.screen.memo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remo.remoappcourse.domain.repository.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel

class MemoListViewModel @Inject constructor(
    private val repository: MemoRepository
) :ViewModel() {

    val memoList = repository.getAllMemos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}