package com.yoo.tasbeh.ui.bottom.menu.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Note(
    val name: String ="",
    val description:String="",
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val savedDate: String="time",
    val isSaved:Boolean=false,
    val currentCount:Int=0,
    val targetCount:Int=33,
    val bestScore:Int=0,
    val isOpenDialog:Boolean=false
):Parcelable
