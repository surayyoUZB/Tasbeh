package com.yoo.tasbeh.ui.bottom.menu.viewModel

import com.yoo.tasbeh.ui.bottom.menu.database.Note

sealed interface NoteEvent {
    data class OnSaveNote(val note: Note):NoteEvent
    data class OnDeleteNote(val note: Note):NoteEvent
    data class OnUpdateNote(val note: Note):NoteEvent
    data class OnGetNoteById(val id: Int):NoteEvent
    sealed class OnGetAllNotes:NoteEvent
}