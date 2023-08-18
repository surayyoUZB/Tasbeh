package com.yoo.tasbeh.ui.bottom.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoo.tasbeh.ui.bottom.menu.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NoteRepository
):ViewModel() {
    var bestScore= mutableStateOf(0)
    var countTarget= mutableStateOf(33)
    var currentCount= mutableStateOf(0)
    var isOpenDialog= mutableStateOf(false)

    init {
        viewModelScope.launch {
            repository.getBestScore().collectLatest {
                bestScore.value = it
            }
        }
        viewModelScope.launch {
            repository.getCountTarget().collectLatest {
                countTarget.value = it
            }
        }
        viewModelScope.launch {
            repository.getCurrentCount().collectLatest {
                currentCount.value = it
            }
        }
        viewModelScope.launch {
            repository.getIsOpenDialog().collectLatest {
                isOpenDialog.value=it
            }
        }

    }

    fun saveBestScore(int: Int){
        viewModelScope.launch {
            repository.saveBestScore(int)
        }
    }
    fun saveCountTarget(int: Int){
        viewModelScope.launch {
            repository.saveCountTarget(int)
        }
    }
    fun saveCurrentCount(int: Int){
        viewModelScope.launch {
            repository.saveCurrentCount(int)
        }
    }
    fun saveIsOpenDialog(boolean: Boolean){
        viewModelScope.launch {
            repository.saveIsOpenDialog(boolean)
        }
    }
}