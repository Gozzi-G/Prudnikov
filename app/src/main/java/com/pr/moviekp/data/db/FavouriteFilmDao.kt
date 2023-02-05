package com.pr.moviekp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pr.moviekp.data.repositories.FilmItemDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteFilmDao {

    @Query("SELECT * FROM film_items")
    fun getFilmList(): List<FilmItemDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFilmItem(filmItemDbModel: FilmItemDbModel)

    @Query("DELETE FROM film_items WHERE id=:filmItemId")
    suspend fun deleteFilmItem(filmItemId: Int)

    @Query("SELECT * FROM film_items WHERE id=:filmItemId LIMIT 1")
    fun getFilmItem(filmItemId: Int): Flow<FilmItemDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmList(listDto: List<FilmItemDbModel>)

}