package com.example.recipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipe.ui.theme.RecipeTheme
import com.example.recipe.ui_components.InfoScreen
import com.example.recipe.ui_components.MainScreen
import com.example.recipe.utils.ListItem

import com.example.recipe.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var item: ListItem? = null

            RecipeTheme {
                NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
                    composable(Routes.MAIN_SCREEN) {
                        MainScreen(context = this@MainActivity) {
                            listItem ->
                            item = listItem
                            navController.navigate(Routes.INFO_SCREEN)
                        }
                    }
                    composable(Routes.INFO_SCREEN) {
                        InfoScreen(item = item!!)
                    }
                }
            }
        }
    }
}

