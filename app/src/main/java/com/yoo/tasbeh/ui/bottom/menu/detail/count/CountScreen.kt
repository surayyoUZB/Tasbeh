package com.yoo.tasbeh.ui.bottom.menu.detail.count

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yoo.tasbeh.R
import com.yoo.tasbeh.customs.CustomDialog
import com.yoo.tasbeh.functions.CustomCircularProgressIndicator
import com.yoo.tasbeh.functions.LottieCongratulation
import com.yoo.tasbeh.ui.bottom.home.HomeViewModel
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.bottom.menu.viewModel.MainViewModel
import com.yoo.tasbeh.ui.bottom.menu.viewModel.NoteEvent
import com.yoo.tasbeh.ui.theme.white
import com.yoo.tasbeh.util.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountScreen(
    note:Note,
    navHostController:NavHostController,
    onUpdateClick: (Note)->Unit,
    onChangeCurrentCount: (Note)->Unit,
    onChangeIsOpenDialog: (Note)->Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            SmallTopAppBar(
                title = { Text(note.name, color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
    ) { padding ->

        ScaffoldTask(
            note = note,
            paddingValues = padding,
            onUpdateClick = onUpdateClick,
            onChangeCurrentCount = onChangeCurrentCount,
            onChangeIsOpenDialog = onChangeIsOpenDialog
        )
    }
}



@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScaffoldTask(
    note:Note,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
    vm: CountViewModel = hiltViewModel(),
    onUpdateClick: (Note)->Unit,
    onChangeCurrentCount: (Note)->Unit,
    onChangeIsOpenDialog: (Note)->Unit
) {

    val scope= rememberCoroutineScope()

    val showDialog = remember { mutableStateOf(false) }

    if (note.currentCount==note.targetCount) {
        LottieCongratulation()
        if (note.targetCount>note.bestScore){
            onUpdateClick(Note(bestScore = note.targetCount))
        }
        if (viewModel.bestScore.value<note.bestScore){
            viewModel.saveBestScore(note.bestScore)
        }
    }

    if (showDialog.value)
        CustomDialog(value = "", setShowDialog = {
            showDialog.value = it
        }) {
            onUpdateClick(
                Note(
                    name= note.name,
                    description = note.description,
                    id = note.id,
                    isSaved = note.isSaved,
                    currentCount = note.currentCount,
                    targetCount = it.toInt(),
                    bestScore = note.bestScore,
                    isOpenDialog = !note.isOpenDialog
                )
            )
        }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary
                        )
                    ),
                    shape = RoundedCornerShape(bottomStart = 35.dp, bottomEnd = 35.dp)
                ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment =Alignment.CenterHorizontally
        ) {
            Text(
                text = "Eng yuqori natija: ${viewModel.bestScore.value}",
                color = Color.White
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "  Yuqori natija: ${note.bestScore}",
                    color = Color.White
                )
                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_menu_book_24),
                        contentDescription ="salovat",
                        tint = Color.White
                    )
                }

            }

        }

        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CustomCircularProgressIndicator(
                Modifier.weight(2f),
                initialValue = note.currentCount,
                maxValue = note.targetCount,
                primaryColor = MaterialTheme.colorScheme.primary,
                secondaryColor = MaterialTheme.colorScheme.onSecondary,
                circleRadius = 200f,
                onPositionChange = {

                }
            )

            Row(
                modifier = Modifier
                    .weight(1.1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                FloatingActionButton(
                    modifier = Modifier
                        .padding(end = 50.dp)
                        .size(50.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    elevation = FloatingActionButtonDefaults.elevation(10.dp),
                    contentColor = white,
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = {
                        onUpdateClick(
                            Note(
                                name= note.name,
                                description = note.description,
                                id = note.id,
                                isSaved = note.isSaved,
                                currentCount = 0,
                                bestScore = note.bestScore,
                                isOpenDialog = true
                            )
                        )
                    },
                ) {
                    Icon(imageVector = Icons.Outlined.Refresh, contentDescription = "refresh")
                }


                FloatingActionButton(
                    modifier = Modifier
                        .padding(start = 50.dp)
                        .size(50.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    elevation = FloatingActionButtonDefaults.elevation(10.dp),
                    contentColor = white,
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = {
                        onUpdateClick(
                            Note(
                                name= note.name,
                                description = note.description,
                                id = note.id,
                                isSaved = note.isSaved,
                                currentCount = 0,
                                targetCount = 33,
                                bestScore = note.bestScore,
                                isOpenDialog = false
                            )
                        )
                    },
                ) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = "delete")

                }


            }

            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 10.dp)
                    .weight(1.6f)
            ) {
                FloatingActionButton(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(50.dp)),
                    elevation = FloatingActionButtonDefaults.elevation(10.dp),
                    contentColor = white,
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = {

                        if (!note.isOpenDialog) {
                            showDialog.value = true
                            onChangeIsOpenDialog(note)

                        } else {
                            if (note.currentCount != note.targetCount) {
                                onChangeCurrentCount(note)
                            } else {
                                onUpdateClick(
                                    Note(
                                        name= note.name,
                                        description = note.description,
                                        id = note.id,
                                        isSaved = note.isSaved,
                                        currentCount = 0,
                                        targetCount = 33,
                                        bestScore = note.bestScore,
                                        isOpenDialog = false
                                    )
                                )
                            }
                        }
                    },
                ) {
                    Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "add")

                }
            }


        }

    }
}