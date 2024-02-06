package com.example.lab4.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab4.viewmodels.home.HomeViewModel
import kotlinx.coroutines.launch
import com.example.lab4.components.*
import com.example.lab4.R
import com.example.lab4.api.Champion
import com.example.lab4.app.ChampionDetailScreen
import com.example.lab4.app.ChampionViewModel
import com.example.lab4.navigation.AppRouter
import com.example.lab4.navigation.Screens
import com.example.lab4.navigation.ButtonHandler

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val championViewModel: ChampionViewModel = viewModel()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        homeViewModel.getUserData()
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppToolbar(
                toolbarTitle = stringResource(id = R.string.home),
                logoutButtonClicked = {
                    homeViewModel.logout()
                },
                navigationIconClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            NavigationDrawerHeader(homeViewModel.emailId.value)
        }

    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            val champions = championViewModel.champions.observeAsState().value
            NavHost(navController = navController, startDestination = "championList") {
                composable("championList") {
                    champions?.let { champData ->
                        ChampionList(champData.data, navController)
                    }
                }
                composable("championDetail/{champId}") { backStackEntry ->
                    champions?.data?.get(backStackEntry.arguments?.getString("champId"))?.let { champion ->
                        ChampionDetailScreen(champion)
                    }
                }
            }
            ButtonHandler {
                AppRouter.navigateTo(Screens.HomeScreens)
            }
        }
    }
}
@Composable
fun ChampionList(champions: Map<String, Champion>, navController: NavController) {
    LazyColumn {
        items(champions.values.toList()) { champion ->
            Text(
                champion.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("championDetail/${champion.id}")
                    }
                    .padding(16.dp)
            )
        }
    }
    ButtonHandler {
        AppRouter.navigateTo(Screens.HomeScreens)
    }
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

