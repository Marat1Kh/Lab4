package com.example.lab4.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab4.viewmodels.ChampionViewModel
import com.example.lab4.navigation.AppRouter
import com.example.lab4.navigation.Screens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(championViewModel: ChampionViewModel = viewModel()) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val updateRequired by championViewModel.versionUpdateRequired.observeAsState()
    val champions by championViewModel.champions.observeAsState()
    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Hello!",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 24.dp, bottom = 32.dp) // Adjust padding as needed
                )
                // Define a consistent button style
                val buttonModifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(50.dp)  // Fixed height for all buttons

                val buttonColors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF8B62F))
                val buttonShape = RoundedCornerShape(20.dp)  // More rounded corners

                Button(
                    onClick = {
                        // When the button is clicked, check for updates
                        if (updateRequired == true) {
                            // Show snackbar instead of AlertDialog
                            scope.launch {
                                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "A new version of the champion data is available. Update?",
                                    actionLabel = "Update",
                                    duration = SnackbarDuration.Indefinite
                                )
                                if (snackbarResult == SnackbarResult.ActionPerformed) {
                                    championViewModel.updateVersionIfNeeded(true)
                                    AppRouter.navigateTo(Screens.HomeScreens)
                                }
                            }
                        } else {
                            AppRouter.navigateTo(Screens.HomeScreens)
                        }
                    },
                    colors = buttonColors,
                    modifier = buttonModifier,
                    shape = buttonShape
                ) {
                    Text(
                        "Champions Info",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

