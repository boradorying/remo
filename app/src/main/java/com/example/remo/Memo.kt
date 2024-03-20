package com.example.remo

data class Memo(
    val id: Int,
    var title: String,
    var content: String,
    var timestamp: Long = System.currentTimeMillis()
)