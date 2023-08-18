@file:Suppress("DEPRECATION")

package com.yoo.tasbeh.functions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.yoo.tasbeh.R

@Composable
fun LottieCongratulation() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.congratulation))
        val progress by animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            composition = composition,
//            progress = progress,
            iterations = LottieConstants.IterateForever
        )

    }
}

@Composable
fun LottieEmptyList(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.empty_list))
        LottieAnimation(
            composition =composition,
            iterations = LottieConstants.IterateForever
        )
    }
}