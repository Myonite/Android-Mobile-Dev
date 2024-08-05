package com.example.mixingstat.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mixingstat.data.dao.CocktailDao
import com.example.mixingstat.data.models.Cocktail

@Database(
    entities = [Cocktail::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class CocktailDatabase
    : RoomDatabase() {
    abstract val dao: CocktailDao
}
