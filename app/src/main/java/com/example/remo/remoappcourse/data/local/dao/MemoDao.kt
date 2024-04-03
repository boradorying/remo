package com.example.remo.remoappcourse.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.remo.remoappcourse.data.local.entity.MemoEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MemoDao {

    @Query("SELECT * FROM MemoEntity")
    fun getAllMemos(): Flow<List<MemoEntity>>

    @Query("""
        SELECT * FROM MemoEntity 
        WHERE id =:id""")
    suspend fun getMemoById(id:Int) : MemoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemo(memoEntity: MemoEntity)

    @Delete
    suspend fun deleteMemo(memoEntity: MemoEntity)
    @Update
    suspend fun updateMemo(memoEntity: MemoEntity)
}