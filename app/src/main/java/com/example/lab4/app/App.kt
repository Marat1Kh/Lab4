package com.example.lab4.app
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.lab4.viewmodels.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab4.navigation.AppRouter
import com.example.lab4.navigation.Screens
import com.example.lab4.screens.SignUpScreen
import com.example.lab4.screens.LoginScreen
import com.example.lab4.screens.MainScreen
import com.example.lab4.screens.HomeScreen
import dagger.hilt.android.HiltAndroidApp


@Composable

fun App(homeViewModel: HomeViewModel = viewModel()) {
    homeViewModel.checkForActiveSession()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            AppRouter.navigateTo(Screens.HomeScreens)
        }
        Crossfade(targetState = AppRouter.currentScreens, label = "") { currentState ->
            when (currentState.value) {
                is Screens.SignUpScreens -> {

                    SignUpScreen()
                }
                is Screens.LoginScreens -> {
                    LoginScreen()
                }
                is Screens.MainScreens -> {
                    MainScreen()
                }
                is Screens.HomeScreens -> {
                    HomeScreen()
                }


            }
        }
    }
}