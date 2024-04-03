package com.example.remo.remoappcourse.data.repository

import com.example.remo.remoappcourse.data.local.dao.MemoDao
import com.example.remo.remoappcourse.data.mapper.asExternalModel
import com.example.remo.remoappcourse.data.mapper.toEntity
import com.example.remo.remoappcourse.domain.model.Memo
import com.example.remo.remoappcourse.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MemoRepositoryImpl(
    private val dao:MemoDao
) :MemoRepository {
    override fun getAllMemos(): Flow<List<Memo>> {
       return dao.getAllMemos()
            .map { memos->
                memos.map {
                    it.asExternalModel()
                }

            }
    }

    override suspend fun getMemoById(id: Int): Memo? {
        return dao.getMemoById(id)?.asExternalModel()
    }

    override suspend fun insertMemo(memo: Memo) {
        return dao.insertMemo(memo.toEntity())
    }

    override suspend fun deleteMemo(memo: Memo) {
        return dao.deleteMemo(memo.toEntity())
    }

    override suspend fun updateMemo(memo: Memo) {
        return dao.updateMemo(memo.toEntity())
    }
}