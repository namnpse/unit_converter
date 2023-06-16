package com.namnp.unitconverter.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.namnp.unitconverter.data.model.ConversionResult

@Database(entities = [ConversionResult::class],version = 1)
abstract class ConverterDatabase : RoomDatabase() {
    abstract val converterDAO : ConverterDAO
}
