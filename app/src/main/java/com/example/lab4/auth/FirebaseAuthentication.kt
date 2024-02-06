package com.example.lab4.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface FireBaseAuthentication {
    fun signInWithEmailAndPassword(email: String, password: String): Task<AuthResult>
}