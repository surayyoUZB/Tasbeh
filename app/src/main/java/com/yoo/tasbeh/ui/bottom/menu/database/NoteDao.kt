package com.yoo.tasbeh.ui.bottom.menu.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("select * from note order by id desc")
    fun getAllNotes():Flow<List<Note>>

    @Query("select * from note where id = :id")
    fun getNoteById(id:Int): Flow<Note?>
}