package com.yoo.tasbeh.ui.bottom.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yoo.tasbeh.R
import com.yoo.tasbeh.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "", color = white) },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary)
            )
        }
    ) { padding ->
        ScaffoldQuiz(paddingValues = padding)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldQuiz(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
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
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.quiz_bg),
                contentDescription = "quiz_image"
            )
        }



      Column(
          modifier = Modifier.weight(1f).fillMaxSize()
      ) {
          val options = listOf("Food", "Bill Payment", "Recharges", "Outing", "Other")

          var expanded by remember { mutableStateOf(false) }
          var selectedOptionText by remember { mutableStateOf(options[0]) }

          ExposedDropdownMenuBox(
              expanded = expanded,
              onExpandedChange = {
                  expanded = !expanded
              }
          ) {
              TextField(
                  readOnly = true,
                  value = selectedOptionText,
                  onValueChange = { },
                  label = { Text("Categories") },
                  trailingIcon = {
                      ExposedDropdownMenuDefaults.TrailingIcon(
                          expanded = expanded
                      )
                  },
                  colors = ExposedDropdownMenuDefaults.textFieldColors()
              )
              ExposedDropdownMenu(
                  expanded = expanded,
                  onDismissRequest = {
                      expanded = false
                  }
              ) {
                  options.forEach { selectionOption ->
                      DropdownMenuItem(
                          text =  {Text(text = selectionOption)},
                          onClick = {
                              selectedOptionText = selectionOption
                              expanded = false
                          }
                      )
                  }
              }
          }
      }










        }
}




