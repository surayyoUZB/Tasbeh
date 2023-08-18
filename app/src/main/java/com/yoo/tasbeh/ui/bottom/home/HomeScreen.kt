package com.yoo.tasbeh.ui.bottom.home


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yoo.tasbeh.R
import com.yoo.tasbeh.customs.CustomDialog
import com.yoo.tasbeh.functions.CustomCircularProgressIndicator
import com.yoo.tasbeh.functions.LottieCongratulation
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {
    val context = LocalContext.current

//    var selectedIndex by remember { mutableStateOf(0) }
//    val drawerIcons= listOf(
//        Icons.Outlined.Home,
//        Icons.Outlined.Menu,
//        Icons.Outlined.Favorite,
//        Icons.Outlined.Info
//    )
//    var drawerState = rememberDrawerState(DrawerValue.Closed)
//    var scope = rememberCoroutineScope()
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//
//            Image(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                painter = painterResource(id = R.drawable.nav4),
//                contentDescription = "nav",
//                alpha = 0.8f,
//            )
//            Spacer(
//                modifier = Modifier
//                    .padding(bottom = 10.dp)
//                    .size(width = 1000.dp, height = 3.dp)
//                    .background(Color.Black)
//
//            )
//
//
//            drawerIcons.forEachIndexed{ index, imageVector->
//                NavigationDrawerItem(
//                    label = {
//                        Icon(
//                            imageVector = imageVector,
//                            contentDescription = imageVector.name
//                        )
//                    },
//                    selected = selectedIndex == index,
//                    onClick = { selectedIndex=index }
//
//                )
//            }
//
//        },
//    ) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            SmallTopAppBar(
                title = { "TASBEH" },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(
                        onClick = {
//                                scope.launch {
//                                    drawerState.open()
//                                }
                        }) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = "menu",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(
                        modifier = Modifier
                            .align(alignment = Alignment.Bottom),
                        onClick = {
                            navHostController.navigate("settings")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "setting",
                            tint = Color.White
                        )
                    }
                }


            )
        },
    ) { padding ->

        ScaffoldTask(paddingValues = padding)

    }

}


//    BackHandler {
//        if (drawerState.isOpen) {
//            scope.launch {
//                drawerState.close()
//            }
//        } else {
//            (context as MainActivity).finish()
//        }
//    }
//}


@Composable
fun ScaffoldTask(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel= hiltViewModel()
) {

    val showDialog = remember { mutableStateOf(false) }

    if (viewModel.countTarget.value == viewModel.currentCount.value) {
        LottieCongratulation()
        if (viewModel.bestScore.value<viewModel.countTarget.value){
            viewModel.saveBestScore(viewModel.countTarget.value)
        }
    }

    if (showDialog.value)
        CustomDialog(value = "", setShowDialog = {
            showDialog.value = it
        }) {
            viewModel.saveCountTarget(it.toInt())
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Text(
                    text = "Eng Yuqori natija: ${viewModel.bestScore.value}",
                    color = Color.White,
                    fontSize = 20.sp
                )

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
                initialValue = viewModel.currentCount.value,
                maxValue = viewModel.countTarget.value,
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
                        viewModel.saveCurrentCount(0)
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
                        viewModel.saveCurrentCount(0)
                        viewModel.saveCountTarget(33)
                        viewModel.saveIsOpenDialog(false)
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

                        if (!viewModel.isOpenDialog.value) {
                            showDialog.value = true

                            viewModel.saveIsOpenDialog(true)


                        } else {
                            if (viewModel.currentCount.value != viewModel.countTarget.value) {
                                val add=viewModel.currentCount.value
                                viewModel.saveCurrentCount(add+1)
                            } else {
                                viewModel.saveCurrentCount(0)
                                viewModel.saveCountTarget(33)
                                viewModel.saveIsOpenDialog(false)
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