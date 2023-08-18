package com.yoo.tasbeh.ui.top.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoo.tasbeh.ui.bottom.menu.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: NoteRepository
):ViewModel() {

    var theme = mutableStateOf(0)

    init {
        viewModelScope.launch {
            repository.getTheme().collectLatest {
                theme.value=it
            }
        }
    }

    fun saveTheme(index:Int){
        viewModelScope.launch {
            repository.saveTheme(index)
        }
    }


}