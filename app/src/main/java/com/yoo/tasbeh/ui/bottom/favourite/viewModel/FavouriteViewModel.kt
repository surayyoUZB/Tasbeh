package com.yoo.tasbeh.ui.bottom.favourite.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNote
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.bottom.menu.repository.NoteRepository
import com.yoo.tasbeh.ui.bottom.menu.viewModel.NoteEvent
import com.yoo.tasbeh.util.getLocalTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    var favNoteList= mutableStateListOf<FavouriteNote>()
    var cachedList= mutableStateListOf<FavouriteNote>()

    private var _noteState= MutableStateFlow(Note())
    val noteState get() = _noteState.asStateFlow()

    fun getFavNoteById(id:Int): Flow<FavouriteNote?> {
        return repository.getFavNoteById(id)
    }

    init {
        viewModelScope.launch {
            repository.getAllFavNotes().collectLatest{
                cachedList.clear()
                cachedList.addAll(it)
                favNoteList.clear()
                favNoteList.addAll(it)
            }
        }
    }

    fun onSearch(query:String){
        if (query.isNotBlank()){
            val filteredList= cachedList.filter {
                it.name.lowercase().trim().startsWith(query.lowercase())
            }
            favNoteList.clear()
            favNoteList.addAll(filteredList)
        }else{
            favNoteList.clear()
            favNoteList.addAll(cachedList)
        }
    }


    fun onEvent(event: FavouriteNoteEvent){
        when(event){
            is FavouriteNoteEvent.OnGetFavNoteById ->{
                viewModelScope.launch(Dispatchers.IO) {
//                    val queryNote=repository.getNoteById(event.id)
//                    note.postValue(queryNote.value)
                }
            }
            is FavouriteNoteEvent.OnSaveFavNote ->{
                viewModelScope.launch(Dispatchers.IO) {
                    repository.saveNote(
                        Note(
                            event.favouriteNote.name,
                            event.favouriteNote.description,
                            event.favouriteNote.id,
                            getLocalTime(),
                            true
                        )
                    )
                }
            }
            is FavouriteNoteEvent.OnDeleteFavNote ->{
                viewModelScope.launch(Dispatchers.IO) {
                    repository.deleteFavNote(event.favouriteNote)
                }
            }
            is FavouriteNoteEvent.OnUpdateFavNote ->{
                viewModelScope.launch(Dispatchers.IO) {
                    repository.updateFavNote(event.favouriteNote)
                }
            }
        }
    }

}