package com.yoo.tasbeh.ui.top.settings


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yoo.tasbeh.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navHostController: NavHostController,
    viewModel: SettingViewModel = hiltViewModel()
) {
    var clickedColor:Color



    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Sozlamalar", color = Color.White) },
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
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(5.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(end = 6.dp),
                        painter = painterResource(id = R.drawable.baseline_color_lens_24),
                        contentDescription = "theme",
                        tint = Color.White
                    )
                    Text(text = "Mavzu", fontSize = 25.sp, color = Color.White)
                }
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(1f)
                            .background(
                                if (viewModel.theme.value == 0) {
                                    Color.Blue.copy(0.3f)
                                } else {
                                    MaterialTheme.colorScheme.primary
                                }, RoundedCornerShape(30.dp)
                            )
                            .clickable {
                                viewModel.saveTheme(0)
                            },
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            painter = painterResource(id = R.drawable.baseline_auto_awesome_24),
                            contentDescription = "theme",
                            tint = Color.White
                        )
                        Text(text = "Sistema", fontSize = 15.sp, color = Color.White)
                    }
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(1f)
                            .background(
                                if (viewModel.theme.value == 1) {
                                    Color.Blue.copy(0.3f)
                                } else {
                                    MaterialTheme.colorScheme.primary
                                }, RoundedCornerShape(30.dp)
                            )
                            .clickable {
                                viewModel.saveTheme(1)
                            },
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            painter = painterResource(id = R.drawable.baseline_light_mode_24),
                            contentDescription = "theme",
                            tint = Color.White
                        )
                        Text(text = "Kun", fontSize = 15.sp, color = Color.White)
                    }
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(1f)
                            .background(
                                if (viewModel.theme.value == 2) {
                                    Color.Blue.copy(0.3f)
                                } else {
                                    MaterialTheme.colorScheme.primary
                                }, RoundedCornerShape(30.dp)
                            )
                            .clickable {
                                viewModel.saveTheme(2)
                            },
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            painter = painterResource(id = R.drawable.baseline_dark_mode_24),
                            contentDescription = "theme",
                            tint = Color.White
                        )
                        Text(text = "Tun", fontSize = 15.sp, color = Color.White)
                    }
                }

            }

        }
    }
}