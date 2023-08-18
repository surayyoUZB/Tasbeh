package com.yoo.tasbeh.di

import android.content.Context
import androidx.room.Room
import com.yoo.tasbeh.manager.DataStoreManager
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNote
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNoteDao
import com.yoo.tasbeh.ui.bottom.menu.database.NoteDao
import com.yoo.tasbeh.ui.bottom.menu.database.NoteDatabase
import com.yoo.tasbeh.ui.bottom.menu.repository.NoteRepository
import com.yoo.tasbeh.ui.bottom.menu.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @[Provides Singleton]
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ):NoteDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note.db"
        ).fallbackToDestructiveMigration().build()
    }

    @[Provides Singleton]
    fun provideDao(database:NoteDatabase):NoteDao{
        return database.dao
    }
    @[Provides Singleton]
    fun provideFavDao(database:NoteDatabase):FavouriteNoteDao{
        return database.favDao
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        noteDao:NoteDao,
        favDao: FavouriteNoteDao,
        dataStoreManager: DataStoreManager
    ):NoteRepository{
        return NoteRepositoryImpl(noteDao = noteDao, favDao = favDao, dataStoreManager = dataStoreManager)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext context: Context
    ):DataStoreManager{
        return DataStoreManager(context)
    }

}