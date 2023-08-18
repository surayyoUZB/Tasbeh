package com.yoo.tasbeh.functions

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yoo.tasbeh.ui.theme.*
import kotlin.math.*
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun CustomCircularProgressIndicator(
    modifier: Modifier = Modifier,
    initialValue:Int,
    primaryColor:Color,
    secondaryColor:Color,
    minValue:Int=0,
    maxValue:Int=99,
    circleRadius:Float,
    onPositionChange:(Int)->Unit
) {
    var circleCenter by remember { mutableStateOf(Offset.Zero) }
//    var positionValue by remember { mutableStateOf(initialValue) }
    
    
    Box(
        modifier = modifier
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ){
            val width=size.width
            val height=size.height
            val circleThickness=width / 35f
            circleCenter=Offset(x = width/2f, y = height/2f)

            drawCircle(
                brush = Brush.radialGradient(
                    listOf(
                        gray,
                        primaryColor.copy(0.7f)
                    )
                ),

                radius = circleRadius,
                center = circleCenter
            )

            drawCircle(
                style = Stroke(
                    width = circleThickness
                ),
                color = secondaryColor,
                radius = circleRadius,
                center = circleCenter
            )






            drawArc(
                color = primaryColor,
                startAngle = 90f,
                sweepAngle = (360f/maxValue) * initialValue.toFloat(),
                style = Stroke(
                    width=circleThickness,
                    cap = StrokeCap.Round
                ),
                useCenter = false,
                size = Size(
                    width=circleRadius * 2f,
                    height=circleRadius * 2f
                ),
                topLeft = Offset(
                    (width - circleRadius *2f)/2f,
                    (height - circleRadius *2f)/2f
                )
            )

            val outerRadius= circleRadius +circleThickness/2f
            val gap=15f
            for (i in 0 .. (maxValue-minValue)){

                val color= if (i<initialValue-minValue) primaryColor else primaryColor.copy(0.3f)
                val angleIndecrees=i*360f/(maxValue-minValue).toFloat()
                val angleInRed = angleIndecrees * PI / 180f + PI/2f

                val yGapAdjustment= cos(angleIndecrees * PI / 180f)*gap
                val xGapAdjustment= -sin(angleIndecrees * PI / 180f)*gap

                val start= Offset(
                    x = (outerRadius * cos(angleInRed) + circleCenter.x +xGapAdjustment).toFloat(),
                    y = (outerRadius * sin(angleInRed) + circleCenter.y +yGapAdjustment).toFloat()
                )

                val end= Offset(
                    x = (outerRadius * cos(angleInRed) + circleCenter.x +xGapAdjustment).toFloat(),
                    y = (outerRadius * sin(angleInRed) + circleThickness +circleCenter.y +yGapAdjustment).toFloat()
                )

                rotate(
                    angleIndecrees,
                    pivot = start
                ){
                    drawLine(
                        color=color,
                        start=start,
                        end=end,
                        strokeWidth = 1.dp.toPx()
                    )
                }


            }

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "$initialValue",
                    circleCenter.x,
                    circleCenter.y-10.dp.toPx()/3f,
                    Paint().apply {
                        textSize = 22.sp.toPx()
                        textAlign=Paint.Align.CENTER
                        color=Color.Black.toArgb()
                        isFakeBoldText = true
                    }
                )

                drawLine(
                    circleCenter.x+80.dp.toPx()/3f,
                    circleCenter.y,
                    circleCenter.x-80.dp.toPx()/3f,
                    circleCenter.y,
                    Paint().apply {
                        color=Color.Black.toArgb()

                    }
                )


                drawText(
                    "$maxValue",
                    circleCenter.x,
                    circleCenter.y+60.dp.toPx()/3f,
                    Paint().apply {
                        textSize=22.sp.toPx()
                        textAlign=Paint.Align.CENTER
                        color=Color.Black.toArgb()
                        isFakeBoldText = true
                    }
                )



            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun preview() {
    CustomCircularProgressIndicator(
        modifier = Modifier
            .size(250.dp)
            .background(white),
        initialValue = 100,
        primaryColor = darkBlue,
        secondaryColor = gray,
        circleRadius = 200f,
        maxValue = 110,
        onPositionChange = {

        }
    )
    
}