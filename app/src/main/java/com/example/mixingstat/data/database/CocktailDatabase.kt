package com.example.mixingstat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mixingstat.data.dao.CocktailDao
import com.example.mixingstat.data.models.Cocktail

@Database(
    entities = [Cocktail::class],
    version = 1,
    exportSchema = false
)
abstract class CocktailDatabase
    : RoomDatabase() {
    abstract val dao: CocktailDao
}