package com.namnp.unitconverter.di

import android.app.Application
import androidx.room.Room
import com.namnp.unitconverter.data.db.ConverterDatabase
import com.namnp.unitconverter.data.repository.ConverterRepository
import com.namnp.unitconverter.data.repository.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConverterDatabase(app:Application) : ConverterDatabase {
         return Room.databaseBuilder(
             app,
             ConverterDatabase::class.java,
             "converter_result_database"
         ).build()
    }

    @Provides
    @Singleton
    fun provideConverterRepository(database : ConverterDatabase) : ConverterRepository {
       return ConverterRepositoryImpl(database.converterDAO)
    }

}