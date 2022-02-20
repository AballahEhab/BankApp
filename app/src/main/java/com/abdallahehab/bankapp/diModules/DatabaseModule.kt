package com.abdallahehab.bankapp.diModules

import android.content.Context
import androidx.room.Room
import com.abdallahehab.bankapp.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context:Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
}