package com.yoo.tasbeh.ui.bottom.favourite.viewModel

import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNote

sealed interface FavouriteNoteEvent {
    data class OnSaveFavNote(val favouriteNote: FavouriteNote):FavouriteNoteEvent
    data class OnDeleteFavNote(val favouriteNote: FavouriteNote):FavouriteNoteEvent
    data class OnUpdateFavNote(val favouriteNote: FavouriteNote):FavouriteNoteEvent
    data class OnGetFavNoteById(val favouriteNote: FavouriteNote):FavouriteNoteEvent
    sealed class OnGetAllFavNotes:FavouriteNoteEvent
}