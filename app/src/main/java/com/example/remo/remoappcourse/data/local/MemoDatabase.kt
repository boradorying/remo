package com.example.remo.remoappcourse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.remo.remoappcourse.data.local.dao.MemoDao
import com.example.remo.remoappcourse.data.local.entity.MemoEntity

@Database(
    version = 1,
    entities = [MemoEntity::class],
    exportSchema = false
    // Room은 런타임에 사용되는 데이터베이스의 스키마(테이블, 열, 인덱스 등의 정의)를 JSON 파일로 내보낼 수 있는 기능을 제공 이 기능은 데이터베이스 버전 관리와 마이그레이션을 도울 때 유용
    //
    //경고 메시지에 따르면 스키마 내보내기 디렉터리가 지정되지 않았기 때문에 Room이 스키마를 내보낼 수 없다고 해서 설정함
)
abstract class MemoDatabase:RoomDatabase() {

    abstract val dao: MemoDao

    companion object {
        const val name = "memo_db"
    }
}