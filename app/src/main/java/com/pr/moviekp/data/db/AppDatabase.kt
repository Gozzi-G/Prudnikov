package com.pr.moviekp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pr.moviekp.data.repositories.FilmItemDbModel

@Database(entities = [FilmItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun favouriteFilmsDao(): FavouriteFilmDao

}