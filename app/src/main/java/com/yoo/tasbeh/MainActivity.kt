package com.yoo.tasbeh


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.yoo.tasbeh.ui.top.settings.SettingViewModel
import com.yoo.tasbeh.ui.navigation.RootNavigation
import com.yoo.tasbeh.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm= hiltViewModel<SettingViewModel>()
            val theme=when(vm.theme.value){
                0-> isSystemInDarkTheme()
                1->false
                else->true
            }
            TasbehTheme(
                darkTheme = theme
            ) {
                val navController= rememberNavController()
                RootNavigation(navHostController = navController)
            }
        }
    }
}

//*#*#4636#*