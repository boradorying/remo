package com.example.remo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MemoViewModel : ViewModel() {
    private val _memos = MutableStateFlow<List<Memo>>(emptyList())
    val memos: StateFlow<List<Memo>> = _memos //jetpack의 더 잘 통합되고,호환성때문에 라이브데이터 안쓰고 null을 가질수 없어서 널체크 할 필요 없음 ,최신상태유지,상태관리 UI,코루틴과통합

    init {
        // 초기 데이터 로딩

    }

    fun addMemo(title: String, content: String) {

        viewModelScope.launch {
            val newMemo = Memo(
                id = _memos.value.size + 1 ,
                title = title,
                content = content
            )
            _memos.value = _memos.value + newMemo
        }
    }

    fun updateMemo(id: Int, title: String, content: String) {
        viewModelScope.launch {
            val updatedMemo = _memos.value.map{ memo ->
                if (memo.id == id) memo.copy(title =title, content = content)else memo
            }
            _memos.value = updatedMemo
        }
    }

    fun deleteMemo(id: Int) {

    }
}
