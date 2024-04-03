package com.example.remo.remoappcourse.data.di

import android.content.Context
import androidx.room.Room
import com.example.remo.remoappcourse.data.local.MemoDatabase
import com.example.remo.remoappcourse.data.repository.MemoRepositoryImpl
import com.example.remo.remoappcourse.domain.repository.MemoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object AppModule  {
    @Provides
    @Singleton
    fun provideMemoDatabase(@ApplicationContext context : Context): MemoDatabase =
        Room.databaseBuilder(
            context,
            MemoDatabase::class.java,
            MemoDatabase.name
        ).build()

    @Provides
    @Singleton
    fun provideMemoRepository(database: MemoDatabase):MemoRepository =
        MemoRepositoryImpl(dao = database.dao)
}