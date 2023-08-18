package com.yoo.tasbeh.ui.bottom.menu.repository

import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNote
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.bottom.menu.database.NoteDao
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun saveNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
    fun getNoteById(id:Int):Flow<Note?>
    fun getAllNotes():Flow<List<Note>>

    suspend fun saveFavNote(favouriteNote: FavouriteNote)
    suspend fun deleteFavNote(favouriteNote: FavouriteNote)
    suspend fun updateFavNote(favouriteNote: FavouriteNote)
    fun getFavNoteById(id:Int):Flow<FavouriteNote?>
    fun getAllFavNotes():Flow<List<FavouriteNote>>

    suspend fun saveTheme(index: Int)
    fun getTheme():Flow<Int>
    suspend fun saveBestScore(int: Int)
    fun getBestScore():Flow<Int>
    suspend fun saveCountTarget(int: Int)
    fun getCountTarget():Flow<Int>
    suspend fun saveCurrentCount(int: Int)
    fun getCurrentCount():Flow<Int>
    suspend fun saveIsOpenDialog(boolean: Boolean)
    fun getIsOpenDialog():Flow<Boolean>




}