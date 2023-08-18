package com.yoo.tasbeh.manager

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(
    private val context:Context
) {
    private val Context.dataStore by preferencesDataStore("my_pref")

    companion object{
        private val THEME= intPreferencesKey("theme")
        private val BEST_SCORE= intPreferencesKey("best_score")
        private val COUNT_TARGET= intPreferencesKey("count_target")
        private val CURRENT_COUNT= intPreferencesKey("current_count")
        private val IS_OPEN_DIALOG= booleanPreferencesKey("is_open_dialog")
    }
    suspend fun saveTheme(index:Int){
        context.dataStore.edit {
            it[THEME]=index
        }
    }
    fun getTheme(): Flow<Int> =context.dataStore.data.map {
        it[THEME]?:0
    }
    suspend fun saveBestScore(int:Int){
        context.dataStore.edit {
            it[BEST_SCORE]=int
        }
    }
    fun getBestScore():Flow<Int> =context.dataStore.data.map {
        it[BEST_SCORE]?:0
    }
    suspend fun saveCountTarget(int: Int){
        context.dataStore.edit {
            it[COUNT_TARGET]=int
        }
    }
    fun getCountTarget():Flow<Int> = context.dataStore.data.map {
        it[COUNT_TARGET]?:33
    }
    suspend fun saveCurrentCount(int: Int){
        context.dataStore.edit {
            it[CURRENT_COUNT]=int
        }
    }
    fun getCurrentCount():Flow<Int> =context.dataStore.data.map {
        it[CURRENT_COUNT]?:0
    }
    suspend fun saveIsOpenDialog(boolean: Boolean){
        context.dataStore.edit {
            it[IS_OPEN_DIALOG]=boolean
        }
    }
    fun getIsOpenDialog():Flow<Boolean> =context.dataStore.data.map {
        it[IS_OPEN_DIALOG]?:false
    }

}