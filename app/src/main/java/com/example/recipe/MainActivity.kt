package com.example.recipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.recipe.ui.theme.RecipeTheme
import com.example.recipe.ui_components.DrawerMenu
import com.example.recipe.ui_components.MainTopBar
import com.example.recipe.utils.DrawerEvents
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()
            val topBarTitle = remember {
                mutableStateOf("Сырники")
            }
            RecipeTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        MainTopBar(
                            title = topBarTitle.value,
                            scaffoldState
                        )
                    },
                    drawerContent = {
                        DrawerMenu() { event ->
                            when(event) {
                                is DrawerEvents.OnItemClick -> {
                                    topBarTitle.value = event.title
                                }
                            }
                            coroutineScope.launch {
                                scaffoldState.drawerState.close()
                            }

                        }
                    }
                ) {

                }
            }
        }
    }
}
