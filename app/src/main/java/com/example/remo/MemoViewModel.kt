package com.example.remo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MemoViewModel : ViewModel() {
    private val memos = mutableStateListOf<Memo>()

    init {
        // 초기 데이터 로딩
        memos.addAll(listOf(
            Memo(1, "Sample Memo 1", "This is the content of Memo 1."),
            Memo(2, "Sample Memo 2", "This is the content of Memo 2.")
        ))
    }

    fun getMemos() = memos

    fun addMemo(title: String, content: String) {
        val newId = (memos.maxOfOrNull { it.id } ?: 0) + 1
        memos.add(Memo(newId, title, content))
    }

    fun updateMemo(id: Int, title: String, content: String) {
        memos.find { it.id == id }?.let {
            it.title = title
            it.content = content
        }
    }

    fun deleteMemo(id: Int) {
        memos.removeAll { it.id == id }
    }
}
