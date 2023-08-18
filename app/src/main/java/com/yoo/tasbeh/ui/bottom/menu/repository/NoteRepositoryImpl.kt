package com.yoo.tasbeh.ui.bottom.menu.repository

import com.yoo.tasbeh.manager.DataStoreManager
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNote
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNoteDao
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.bottom.menu.database.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val  noteDao:NoteDao,
    private val favDao: FavouriteNoteDao,
    private val dataStoreManager: DataStoreManager
): NoteRepository {
    override suspend fun saveNote(note: Note) {
        noteDao.saveNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun updateNote(note: Note) {
       noteDao.updateNote(note)
    }

    override fun getNoteById(id: Int):Flow<Note?> {
        return noteDao.getNoteById(id)
    }

    override fun getAllNotes():Flow<List<Note>> {
        return noteDao.getAllNotes()
    }
    override suspend fun saveFavNote(favouriteNote: FavouriteNote) {
        favDao.saveFavNote(favouriteNote)
    }

    override suspend fun deleteFavNote(favouriteNote: FavouriteNote) {
        favDao.deleteFavNote(favouriteNote)
    }

    override suspend fun updateFavNote(favouriteNote: FavouriteNote) {
        favDao.updateFavNote(favouriteNote)
    }

    override fun getFavNoteById(id: Int):Flow<FavouriteNote?> {
        return favDao.getFavNoteById(id)
    }

    override fun getAllFavNotes():Flow<List<FavouriteNote>> {
        return favDao.getAllFavNotes()
    }

    override suspend fun saveTheme(index: Int) {
        dataStoreManager.saveTheme(index)
    }

    override fun getTheme(): Flow<Int> {
        return dataStoreManager.getTheme()
    }

    override suspend fun saveBestScore(int: Int) {
        dataStoreManager.saveBestScore(int)
    }

    override fun getBestScore(): Flow<Int> {
        return dataStoreManager.getBestScore()
    }

    override suspend fun saveCountTarget(int: Int) {
        dataStoreManager.saveCountTarget(int)
    }

    override fun getCountTarget(): Flow<Int> {
        return dataStoreManager.getCountTarget()
    }

    override suspend fun saveCurrentCount(int: Int) {
        dataStoreManager.saveCurrentCount(int)
    }

    override fun getCurrentCount(): Flow<Int> {
        return dataStoreManager.getCurrentCount()
    }

    override suspend fun saveIsOpenDialog(boolean: Boolean) {
        dataStoreManager.saveIsOpenDialog(boolean)
    }

    override fun getIsOpenDialog(): Flow<Boolean> {
        return dataStoreManager.getIsOpenDialog()
    }
}