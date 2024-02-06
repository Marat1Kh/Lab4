package com.example.lab4.viewmodels.login

data class LoginUIState(
    var email: String = "",
    var password: String = "",
    var emailError: Boolean = false,
    var passwordError: Boolean = false
)