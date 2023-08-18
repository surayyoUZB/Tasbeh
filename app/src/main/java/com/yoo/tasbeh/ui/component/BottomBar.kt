package com.yoo.tasbeh.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.yoo.tasbeh.R

sealed class BottomBar(
    val icon:ImageVector,
    val icon2:Int,
    val route:String
){
    object Home:BottomBar(
        Icons.Outlined.Home,
        0,
        "home"
    )
    object Menu:BottomBar(
        Icons.Outlined.Menu,
        0,
        "menu"
    )
    object Favourite:BottomBar(
        Icons.Outlined.FavoriteBorder,
        0,
        "favourite"
    )
    object Quiz:BottomBar(
        Icons.Outlined.DateRange,
        R.drawable.quiz,
        "quiz"
    )


}
