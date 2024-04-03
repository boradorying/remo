package com.example.remo.remoappcourse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.remo.remoappcourse.data.local.dao.MemoDao
import com.example.remo.remoappcourse.data.local.entity.MemoEntity

@Database(
    version = 1,
    entities = [MemoEntity::class]
)
abstract class MemoDatabase:RoomDatabase() {

    abstract val dao: MemoDao

    companion object {
        const val name = "memo_db"
    }
}