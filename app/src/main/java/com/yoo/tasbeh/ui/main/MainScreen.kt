package com.yoo.tasbeh.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yoo.tasbeh.ui.bottom.menu.viewModel.MainViewModel
import com.yoo.tasbeh.ui.component.BottomAppBar
import com.yoo.tasbeh.ui.navigation.MainGraph
import com.yoo.tasbeh.util.Graph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navHostController: NavHostController= rememberNavController(),
    viewModel: MainViewModel
) {
    val current by navHostController.currentBackStackEntryAsState()
    
    Scaffold(
        bottomBar ={
            BottomAppBar(navHostController = navHostController)
        },
        floatingActionButton = {
            if (current?.destination?.route=="menu"){
                FloatingActionButton(
                    onClick = {
                        navHostController.navigate(Graph.ADD)
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "add")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {padding->
        MainGraph(
            modifier = Modifier.padding(padding),
            navHostController = navHostController,
            viewModel = viewModel
        )

    }

}