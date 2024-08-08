package com.example.mixingstat.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mixingstat.data.dao.CocktailDao
import com.example.mixingstat.data.models.Cocktail

/**
 * The Room database for the application.
 * This is an abstract class where the database configuration is defined.
 * It includes the list of entities and the version of the database.
 * It also includes the list of migrations to be performed when upgrading the database version.
 *
 * @property dao The data access object (DAO) to interact with the database.
 */
@Database(
    // The list of entity classes in the database.
    entities = [Cocktail::class],
    // The version of the database.
    version = 2,
    // Whether to export the schema into a file in the application's data directory for debugging purposes.
    exportSchema = true,
    // The list of auto migrations to be performed when upgrading the database version.
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class CocktailDatabase
    : RoomDatabase() {
    // The data access object (DAO) to interact with the database.
    abstract val dao: CocktailDao
}