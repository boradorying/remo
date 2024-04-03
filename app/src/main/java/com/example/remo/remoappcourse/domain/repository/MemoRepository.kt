package com.example.remo.remoappcourse.domain.repository

import com.example.remo.remoappcourse.domain.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    fun getAllMemos(): Flow<List<Memo>>

    suspend fun getMemoById(id:Int):Memo?

    suspend fun insertMemo(memo: Memo)

    suspend fun deleteMemo(memo: Memo)

    suspend fun updateMemo(memo: Memo)
}