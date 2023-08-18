package com.yoo.tasbeh.ui.bottom.menu.detail.count

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.bottom.menu.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(note)
        }
    }

    fun changeCurrentCount(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(
                Note(
                    name= note.name,
                    description = note.description,
                    id = note.id,
                    savedDate = note.savedDate,
                    isSaved = note.isSaved,
                    currentCount = note.currentCount+1,
                    targetCount = note.targetCount,
                    bestScore = note.bestScore,
                    isOpenDialog = note.isOpenDialog
                )
            )
        }
    }

    fun changeIsOpenDialog(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(
                Note(
                    name= note.name,
                    description = note.description,
                    id = note.id,
                    savedDate = note.savedDate,
                    isSaved = note.isSaved,
                    currentCount = note.currentCount,
                    targetCount = note.targetCount,
                    bestScore = note.bestScore,
                    isOpenDialog = !note.isOpenDialog
                )
            )
        }
    }




}