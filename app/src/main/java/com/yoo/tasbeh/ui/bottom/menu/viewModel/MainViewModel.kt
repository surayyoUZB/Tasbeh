package com.yoo.tasbeh.ui.bottom.menu.viewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNote
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.bottom.menu.repository.NoteRepository
import com.yoo.tasbeh.util.getLocalTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NoteRepository
):ViewModel() {

    var noteList= mutableStateListOf<Note>()
    var cachedList= mutableStateListOf<Note>()

    fun getNoteById(id:Int):Flow<Note?>{
        return repository.getNoteById(id)
    }

    init {
        viewModelScope.launch {
            repository.getAllNotes().collectLatest{
                cachedList.clear()
                cachedList.addAll(it)
                noteList.clear()
                noteList.addAll(it)
            }
        }
    }

    fun onSearch(query:String){
        if (query.isNotBlank()){
            val filteredList= cachedList.filter {
                it.name.lowercase().trim().startsWith(query.lowercase())
            }
            noteList.clear()
            noteList.addAll(filteredList)
        }else{
            noteList.clear()
            noteList.addAll(cachedList)
        }
    }


    fun onEvent(event: NoteEvent){
        when(event){
            is NoteEvent.OnGetNoteById ->{
                viewModelScope.launch(Dispatchers.IO) {
                    val queryNote=repository.getNoteById(event.id)
//                    note.postValue(queryNote.value)
                }
            }
            is NoteEvent.OnSaveNote ->{
                viewModelScope.launch(Dispatchers.IO) {
                    repository.saveNote(
                        Note(
                            event.note.name,
                            event.note.description,
                            event.note.id,
                            getLocalTime(),
                            false
                        )
                    )
                }
            }
            is NoteEvent.OnDeleteNote ->{
                viewModelScope.launch(Dispatchers.IO) {
                    repository.deleteNote(event.note)
                }
            }
            is NoteEvent.OnUpdateNote ->{
                viewModelScope.launch(Dispatchers.IO) {
                    repository.updateNote(event.note)
                }
            }
        }
    }

    fun changeFavourite(note:Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(Note(note.name, note.description,note.id, note.savedDate, !note.isSaved,note.currentCount, note.targetCount, note.bestScore, note.isOpenDialog))
            if (!note.isSaved){
                repository.saveFavNote(FavouriteNote(note.name, note.description, note.id, note.savedDate, note.isSaved))
            }else{
                repository.deleteFavNote(FavouriteNote(note.name, note.description, note.id, note.savedDate, false))
            }
        }
    }

}