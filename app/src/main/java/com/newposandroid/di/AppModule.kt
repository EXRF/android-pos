package com.newposandroid.di

import android.content.Context
import androidx.room.Room
import com.newposandroid.data.locale.PosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCoroutinesContext(): CoroutineContext {
        val numberOfCores = Runtime.getRuntime().availableProcessors()
        val threadPool = Executors.newFixedThreadPool(numberOfCores).asCoroutineDispatcher()
        return threadPool + SupervisorJob()
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, PosDatabase::class.java,"PosDatabase"
    ).build()
}