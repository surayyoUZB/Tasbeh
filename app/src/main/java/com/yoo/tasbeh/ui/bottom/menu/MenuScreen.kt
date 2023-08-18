package com.yoo.tasbeh.ui.bottom.menu


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yoo.tasbeh.MainActivity
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNote
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.bottom.menu.viewModel.MainViewModel
import com.yoo.tasbeh.util.getLocalTime
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    viewModel:MainViewModel= hiltViewModel(),
    onItemClick: (Note) -> Unit
) {
    val context = LocalContext.current as MainActivity
    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            value = search,
            onValueChange = {
                search = it
                viewModel.onSearch(search)
            },
            placeholder = { Text(text = "Qidiruv") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Search
            ),
            maxLines = 1,
            trailingIcon = {
                IconButton(onClick = {
                    search = ""
                    viewModel.onSearch("")
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "search",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "search",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            colors = TextFieldDefaults.textFieldColors(MaterialTheme.colorScheme.primary),
            )

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(
                items = viewModel.noteList,
                key = { i, n -> n.id }
            ) { i, n ->
                NoteItem(
                    note = n,
                    index = i,
                    onItemClick = {
                        onItemClick(it)
                    },
                    onFavouriteClick = {
                        viewModel.changeFavourite(note = it)
                    }
                )

            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: Note,
    index: Int,
    onItemClick: (Note) -> Unit,
    onFavouriteClick: (Note) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .border(
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                RoundedCornerShape(12.dp)
            )
            .padding(5.dp)
            .clickable { onItemClick(note) },
        verticalAlignment = Alignment.CenterVertically
    ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.1f),
                text = index.plus(1).toString(),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = note.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1
            )
            Column{
                IconButton(
                    onClick = { onFavouriteClick(note)}
                ) {
                    Icon(
                        imageVector = if (note.isSaved){Icons.Outlined.Favorite}else{Icons.Outlined.FavoriteBorder},
                        contentDescription = "favourite",
                        tint = if (note.isSaved){Color.Red}else{Color.Blue})
                }
                Text(
                    text = note.savedDate,
                    fontSize = 10.sp,
                    maxLines = 1
                )
            }
        }
    }