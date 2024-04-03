package com.example.remo.remoappcourse.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
 data class MemoEntity (
    @PrimaryKey val id : Int?,
    val title : String,
    val content : String,
)