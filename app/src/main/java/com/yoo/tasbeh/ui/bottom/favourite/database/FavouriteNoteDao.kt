package com.yoo.tasbeh.ui.bottom.favourite.database

import androidx.room.*
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteNoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFavNote(favouriteNote: FavouriteNote)

    @Delete
    suspend fun deleteFavNote(favouriteNote: FavouriteNote)

    @Update
    suspend fun updateFavNote(favouriteNote: FavouriteNote)

    @Query("select * from FavouriteNote order by id desc")
    fun getAllFavNotes(): Flow<List<FavouriteNote>>

    @Query("select * from FavouriteNote where id = :id")
    fun getFavNoteById(id:Int): Flow<FavouriteNote?>
}