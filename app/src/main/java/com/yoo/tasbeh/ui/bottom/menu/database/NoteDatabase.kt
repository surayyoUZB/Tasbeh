package com.yoo.tasbeh.ui.bottom.menu.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNote
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNoteDao


@Database(entities = [Note::class, FavouriteNote::class], version = 5, exportSchema = true )
abstract class NoteDatabase:RoomDatabase() {

    abstract val dao: NoteDao
    abstract val favDao: FavouriteNoteDao

//    companion object{
//        @Volatile
//        private var instance:NoteDatabase?=null
//
//        operator fun invoke(context: Context) = instance?: synchronized(Any()){
//            instance?:createDatabase(context).also{
//                instance=it
//            }
//        }
//
//        private fun createDatabase(context: Context):NoteDatabase{
//            return Room.databaseBuilder(
//                context.applicationContext,
//                NoteDatabase::class.java,
//                "note.db"
//            ).fallbackToDestructiveMigration().build()
//        }
//
//    }
}