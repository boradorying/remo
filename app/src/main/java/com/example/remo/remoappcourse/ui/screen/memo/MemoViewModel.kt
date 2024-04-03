package com.example.remo.remoappcourse.ui.screen.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remo.remoappcourse.domain.model.Memo
import com.example.remo.remoappcourse.domain.repository.MemoRepository
import com.example.remo.remoappcourse.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MemoViewModel @Inject constructor(
    private val repository:MemoRepository
): ViewModel() {

    private val _state = MutableStateFlow(MemoState())
            val state = _state.asStateFlow()

    private val _event = Channel<UiEvent> ()
    val event =_event.receiveAsFlow()

    fun sendEvent(event:UiEvent){
        viewModelScope.launch {
            _event.send(event)
        }
    }

    fun onEvent(event: MemoEvent) {
        when(event){
            is MemoEvent.ContentChagne ->
                _state.update {
                    it.copy(
                        content = event.value
                    )
                }
            MemoEvent.NavigateBack ->sendEvent(UiEvent.NavigateBack)
            MemoEvent.Save ->{
                viewModelScope.launch {
                    val state = state.value
                    val memo =Memo(
                        id= state.id,
                        title = state.title,
                        content = state.content
                    )
                    if (state.id == null){
                        repository.insertMemo(memo)

                    }else{
                        repository.updateMemo(memo)
                    }
                }

            }
            is MemoEvent.TitleChange -> _state.update {
                it.copy(
                    title = event.value
                )
            }
        }
    }
}