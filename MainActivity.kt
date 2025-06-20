package com.example.homelibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.homelibrary.ui.AddEditBookScreen
import com.example.homelibrary.ui.MainScreen
import com.example.homelibrary.viewmodel.BookViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: BookViewModel = viewModel()

            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    MainScreen(viewModel,
                        onAddBook = {
                            navController.navigate("addEdit")
                        },
                        onEditBook = { bookId ->
                            navController.navigate("addEdit/$bookId")
                        }
                    )
                }
                composable(
                    route = "addEdit/{bookId?}",
                    arguments = listOf(navArgument("bookId") {
                        type = NavType.IntType
                        defaultValue = -1
                        nullable = true
                    })
                ) { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getInt("bookId") ?: -1
                    val book = if (bookId != -1) {
                        viewModel.allBooks.value?.find { it.id == bookId }
                    } else null

                    AddEditBookScreen(
                        book = book,
                        onSave = {
                            if (book == null) viewModel.insert(it) else viewModel.update(it)
                            navController.popBackStack()
                        },
                        onCancel = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

