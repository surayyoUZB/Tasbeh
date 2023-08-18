package com.yoo.tasbeh.ui.bottom.menu.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.*
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    note: Note,
    onUpdateClick: (Note) -> Unit,
    onDeleteClick: (Note) -> Unit,
    onNavigateToHome:() ->Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = note.name) },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "back",
                            tint = white
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onUpdateClick(note) }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Create,
                            contentDescription = "update",
                            tint = white
                        )
                    }
                    IconButton(onClick = { onDeleteClick(note) }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "delete",
                            tint = white
                        )
                    }
                },


            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(70.dp),
                onClick = {
                    onNavigateToHome()
                },
                contentColor = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(painter = painterResource(id = com.yoo.tasbeh.R.drawable.tasbeeh_icon), contentDescription = "count")

            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "      ${note.description}")
            Text(text = "  current count:     ${note.currentCount}")
            Text(text = "   targetCount:   ${note.targetCount}")
            Text(text = "   bestScore:    ${note.bestScore}")
            Text(text = "   isOpenDialog:   ${note.isOpenDialog}")

        }

    }

}