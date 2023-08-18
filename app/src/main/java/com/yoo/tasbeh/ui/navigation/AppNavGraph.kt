package com.yoo.tasbeh.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yoo.tasbeh.ui.top.settings.SettingsScreen
import com.yoo.tasbeh.ui.bottom.favourite.FavouriteScreen
import com.yoo.tasbeh.ui.bottom.favourite.database.FavouriteNote
import com.yoo.tasbeh.ui.bottom.favourite.viewModel.FavouriteNoteEvent
import com.yoo.tasbeh.ui.bottom.favourite.viewModel.FavouriteViewModel
import com.yoo.tasbeh.ui.bottom.home.HomeScreen
import com.yoo.tasbeh.ui.bottom.menu.MenuScreen
import com.yoo.tasbeh.ui.bottom.menu.add.AddScreen
import com.yoo.tasbeh.ui.bottom.menu.database.Note
import com.yoo.tasbeh.ui.bottom.menu.detail.DetailScreen
import com.yoo.tasbeh.ui.bottom.menu.detail.count.CountScreen
import com.yoo.tasbeh.ui.bottom.menu.detail.count.CountViewModel
import com.yoo.tasbeh.ui.bottom.menu.viewModel.MainViewModel
import com.yoo.tasbeh.ui.bottom.menu.viewModel.NoteEvent
import com.yoo.tasbeh.ui.bottom.quiz.QuizScreen
import com.yoo.tasbeh.ui.component.BottomBar
import com.yoo.tasbeh.util.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainGraph(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: MainViewModel= hiltViewModel(),
    vm:CountViewModel= hiltViewModel(),
    favViewModel:FavouriteViewModel= hiltViewModel()
) {
    val scope= rememberCoroutineScope()

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = BottomBar.Home.route
    ) {
        composable(
            route = BottomBar.Home.route
        ) {
            HomeScreen(navHostController)
        }
        composable(route = BottomBar.Menu.route) {
            MenuScreen(
                onItemClick = {
                    navHostController.navigate(Graph.DETAIL.plus(
                        "/${it.name}/${it.description}/${it.id}/${it.savedDate}/true/${it.currentCount.toString()}/${it.targetCount.toString()}/${it.bestScore.toString()}/${it.isOpenDialog.toString()}"
                    ))
                }
            )
        }
        composable(route = BottomBar.Favourite.route) {
            FavouriteScreen(
                viewModel = favViewModel,
                onItemClick = {
                    navHostController.navigate(Graph.DETAIL.plus(
                        "/${it.name}/${it.description}/${it.id}/${it.savedDate}/true/${it.currentCount.toString()}/${it.targetCount.toString()}/${it.bestScore.toString()}/${it.isOpenDialog.toString()}"))
                },
                onFavouriteClick = {favNote->
                    favViewModel.onEvent(FavouriteNoteEvent.OnDeleteFavNote(favNote))
                    viewModel.onEvent(NoteEvent.OnUpdateNote(Note(favNote.name, favNote.description, favNote.id, favNote.savedDate, false)))
                }
            )
        }
        composable(route = BottomBar.Quiz.route) {
            QuizScreen()
        }
        composable(
            route="count_screen/{name}/{description}/{id}/{saved_date}/{isSaved}/{current_count}/{target_count}/{best_score}/{is_open_dialog}",
            arguments = listOf(
            navArgument(
                name = "name"
            ) {
                type = NavType.StringType
            },
            navArgument(
                name = "description"
            ) {
                type = NavType.StringType
            },
            navArgument(
                name = "id"
            ) {
                type = NavType.LongType
            },
            navArgument(
                name = "saved_date"
            ) {
                type = NavType.StringType
            },
            navArgument(
                name = "isSaved"
            ) {
                type = NavType.StringType
            },
            navArgument(
                name = "current_count"
            ) {
                type = NavType.StringType
            },
            navArgument(
                name = "target_count"
            ) {
                type = NavType.StringType
            },
            navArgument(
                name = "best_score"
            ) {
                type = NavType.StringType
            },
            navArgument(
                name = "is_open_dialog"
            ) {
                type = NavType.StringType
            }
        )
        ) { it ->
            val name = it.arguments?.getString("name") ?: "null"
            val description = it.arguments?.getString("description") ?: "null"
            val noteId=it.arguments?.getLong("id")?:0
            val savedDate=it.arguments?.getString("saved_date")?:"00:00"
            val isSaved=it.arguments?.getString("isSaved")?:"false"
            val currentCount=it.arguments?.getString("current_count")?:"0"
            val targetCount=it.arguments?.getString("target_count")?:"33"
            val bestScore=it.arguments?.getString("best_score")?:"0"
            val isOpenDialog=it.arguments?.getString("is_open_dialog")?:"false"
            val note=Note(
                name = name,
                description=description,
                id=noteId,
                savedDate=savedDate,
                isSaved=isSaved.toBoolean(),
                currentCount = currentCount.toInt(),
                targetCount = targetCount.toInt(),
                bestScore = bestScore.toInt(),
                isOpenDialog = isOpenDialog.toBoolean()
            )

            CountScreen(
                note = note,
                navHostController=navHostController,
                onUpdateClick = {n->
                    vm.updateNote(n)
                },
                onChangeCurrentCount = {n->
                    vm.changeCurrentCount(n)
                    Log.d("##note", "ScaffoldTask: $n")
                },
                onChangeIsOpenDialog ={n->
                    vm.changeIsOpenDialog(n)
                }
            )
        }
        composable(
            route = "add_screen/{id}",
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            AddScreen(
                id = id,
                back = {
                    navHostController.popBackStack()
                },
                viewModel = viewModel,
                navHostController = navHostController
            )
        }
        composable(
            route = Graph.DETAIL.plus("/{name}/{description}/{id}/{saved_date}/{isSaved}/{current_count}/{target_count}/{best_score}/{is_open_dialog}"),
            arguments = listOf(
                navArgument(
                    name = "name"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "description"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "id"
                ) {
                    type = NavType.LongType
                },
                navArgument(
                    name = "saved_date"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "isSaved"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "current_count"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "target_count"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "best_score"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "is_open_dialog"
                ) {
                    type = NavType.StringType
                }
            )
        ) {
            val name = it.arguments?.getString("name") ?: "null"
            val description = it.arguments?.getString("description") ?: "null"
            val noteId=it.arguments?.getLong("id")?:0
            val savedDate=it.arguments?.getString("saved_date")?:"00:00"
            val isSaved=it.arguments?.getString("isSaved")?:"false"
            val currentCount=it.arguments?.getString("current_count")?:"0"
            val targetCount=it.arguments?.getString("target_count")?:"33"
            val bestScore=it.arguments?.getString("best_score")?:"0"
            val isOpenDialog=it.arguments?.getString("is_open_dialog")?:"false"
            DetailScreen(
                note = Note(
                    name = name,
                    description=description,
                    id=noteId,
                    savedDate=savedDate,
                    isSaved=isSaved.toBoolean(),
                    currentCount = currentCount.toInt(),
                    targetCount = targetCount.toInt(),
                    bestScore = bestScore.toInt(),
                    isOpenDialog = isOpenDialog.toBoolean()
                ),
                navHostController = navHostController,
                onUpdateClick = {note->
                    navHostController.navigate("add_screen/${note.id}")
                },
                onDeleteClick = {note->
                    viewModel.onEvent(NoteEvent.OnDeleteNote(note))
                    favViewModel.onEvent(FavouriteNoteEvent.OnDeleteFavNote(FavouriteNote(note.name, note.description, note.id, note.savedDate, note.isSaved)))
                    navHostController.popBackStack()
                },
                onNavigateToHome = {
                    navHostController.navigate(
                        "count_screen/${name}/${description}/${noteId}/$savedDate/$isSaved/$currentCount/$targetCount/$bestScore/$isOpenDialog")
                }
            )
        }

        addGraph(navHostController, viewModel)
        addSetting(navHostController)
    }

}


fun NavGraphBuilder.addGraph(
    navHostController: NavHostController,
    viewModel: MainViewModel
) {
    navigation(
        startDestination = "add_screen",
        route = Graph.ADD
    ) {
        composable(
            route = "add_screen"
        ) {
            AddScreen(
                id = 0,
                back = {
                    navHostController.popBackStack()
                },
                viewModel = viewModel,
                navHostController = navHostController
            )
        }
    }
}

fun NavGraphBuilder.addSetting(
    navHostController: NavHostController
){
    navigation(
        startDestination = BottomBar.Home.route,
        route= Graph.MAIN
    ){
        composable(
            route="settings"
        ){
            SettingsScreen(navHostController)
        }
    }
}