package com.yoo.tasbeh.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.yoo.tasbeh.util.Graph
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {

    var isLaunched by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        isLaunched = true
        delay(3000)
        navHostController.navigate(Graph.MAIN)
    }
    val animation by animateFloatAsState(
        targetValue = if (isLaunched) 1f else 0f,
        animationSpec = tween(2000), label = "test_label"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primary.copy(0.7f),
                        MaterialTheme.colorScheme.primary
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = com.yoo.tasbeh.R.drawable.tasbeeh_icon),
            contentDescription = "image",
            modifier = Modifier
                .size(250.dp)
                .alpha(animation),
            tint = Color.White
        )

        Text(
            modifier = Modifier.padding(top=50.dp).alpha(animation),
            text = "TASBEH",
            color = Color.White,
            fontSize = 30.sp,
            fontStyle = FontStyle(12)
        )


    }

}