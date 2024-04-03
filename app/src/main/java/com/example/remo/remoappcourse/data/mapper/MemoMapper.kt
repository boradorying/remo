package com.example.remo.remoappcourse.data.mapper

import com.example.remo.remoappcourse.data.local.entity.MemoEntity
import com.example.remo.remoappcourse.domain.model.Memo

fun MemoEntity.asExternalModel():Memo = Memo(
    id,title,content
)

fun Memo.toEntity(): MemoEntity = MemoEntity(
    id,title,content
)