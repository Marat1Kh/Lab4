package com.example.lab4.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screens(val route: String){
    object SignUpScreens: Screens("signup_screen")
    object LoginScreens: Screens("login_screen")
    object HomeScreens: Screens("home_screen")
    object MainScreens: Screens("main_screen")

}

object AppRouter{
    var currentScreens: MutableState<Screens> = mutableStateOf(Screens.SignUpScreens)
    fun navigateTo(destination: Screens){
        currentScreens.value = destination
    }
}
