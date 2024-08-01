package com.example.task.ui.sign_in

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {
    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    fun updateEmail(updatedEmail: String) {
        _signInState.update { currentState ->
            currentState.copy(
                email = updatedEmail,
            )
        }
        validateEmail()
    }


    private fun validateEmail() {
        _signInState.update { currentState ->
            val message: String? = when {
                currentState.email.isBlank() -> "email field is required"
                !Patterns.EMAIL_ADDRESS.matcher(currentState.email)
                    .matches() -> "incorrect email format"

                else -> null
            }
            currentState.copy(
                emailError = message
            )
        }
    }


    fun updatePassword(updatedPassword: String) {
        _signInState.update { currentState ->
            currentState.copy(password = updatedPassword)
        }
        validatePassword()
    }

    private fun validatePassword() {
        _signInState.update { currentState ->
            val message: String? = when {
                currentState.password.isBlank() -> "password field is required"
                currentState.password.length < 8 -> "password must be at least 8 characters"
                else -> null
            }
            currentState.copy(
                passwordError = message
            )
        }
    }

    fun signIn() {
        validateEmail()
        validatePassword()
    }
}