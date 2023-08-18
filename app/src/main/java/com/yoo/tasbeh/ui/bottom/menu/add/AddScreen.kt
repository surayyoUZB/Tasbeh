package com.yoo.tasbeh.ui.bottom.menu.add

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yoo.tasbeh.MainActivity
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNote
import com.yoo.tasbeh.ui.bottom.favourite.viewModel.FavouriteNoteEvent
import com.yoo.tasbeh.ui.bottom.favourite.viewModel.FavouriteViewModel
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.bottom.menu.viewModel.MainViewModel
import com.yoo.tasbeh.ui.bottom.menu.viewModel.NoteEvent
import com.yoo.tasbeh.ui.component.BottomBar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddScreen(
    id: Int,
    navHostController: NavHostController,
    back: () -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
    favViewModel: FavouriteViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf("") }
    var name1 by remember { mutableStateOf("") }
    var detail1 by remember { mutableStateOf("") }
    var isSaved by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var note by remember { mutableStateOf<Note?>(null) }
    var favNote by remember { mutableStateOf<FavouriteNote?>(null) }
    val snackBar = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current as MainActivity

    if (id != 0) {
        LaunchedEffect(key1 = Unit) {
            viewModel.getNoteById(id).collectLatest {
                note=it
            }
            favViewModel.getFavNoteById(id).collectLatest {
                favNote=it
            }
        }
    }
   if(note!= null || favNote!=null){
       note?.let {
           name = it.name
           detail = it.description
           isSaved=it.isSaved
       }
       favNote?.let {
           name=it.name
           detail=it.description
       }
   }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (id == 0) "Qo'shish" else "Taxrirlash") },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(
                        onClick = back
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (name.isNotBlank() && detail.isNotBlank()) {
                            keyboardController?.hide()
                            if (id == 0) {
                                viewModel.onEvent(NoteEvent.OnSaveNote(Note(name, detail)))
                                navHostController.popBackStack()
                                scope.launch {
//                                    snackBar.showSnackbar("salovat muviffaqiyatli saqlandi!!!")
                                    Toast.makeText(
                                        context,
                                        "salovat muviffaqiyatli saqlandi!!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                viewModel.onEvent(NoteEvent.OnUpdateNote(Note(name=name, description = detail, id=id.toLong(), isSaved = isSaved)))
                                favViewModel.onEvent(FavouriteNoteEvent.OnUpdateFavNote(FavouriteNote(name=name, description = detail, id=id.toLong())))
                                navHostController.navigate(BottomBar.Menu.route)
                                scope.launch {
//                                    snackBar.showSnackbar("Salovat muvoffaqiyatli yangilandi")
                                    Toast.makeText(
                                        context,
                                        "Salovat muvoffaqiyatli yangilandi!!!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }
                        } else {
                            scope.launch {
                                Toast.makeText(
                                    context,
                                    "Ma'lumotlarni to'liq kiriting",
                                    Toast.LENGTH_SHORT
                                ).show()
                                keyboardController?.hide()
                            }
                        }
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "check",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                label = {
                    Text(text = "Salovat nomi")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))


            OutlinedTextField(
                value = detail,
                onValueChange = { detail = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                label = {
                    Text(text = "Salovat")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))


        }

    }

}
