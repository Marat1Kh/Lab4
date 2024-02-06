package com.example.lab4.viewmodels.home
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.example.lab4.navigation.AppRouter
import com.example.lab4.navigation.Screens

class HomeViewModel : ViewModel() {
    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    //функция logout
    fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {

                AppRouter.navigateTo(Screens.LoginScreens)
            } else {
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)

    }
    //проверяем активную сессию пользователя
    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            isUserLoggedIn.value = true
        } else {
            isUserLoggedIn.value = false
        }
    }


    val emailId: MutableLiveData<String> = MutableLiveData()
    fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
        }
    }
}