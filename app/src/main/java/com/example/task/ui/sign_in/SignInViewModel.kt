package com.example.task.ui.sign_in

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import com.example.task.R
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
            @StringRes val message: Int? = when {
                currentState.email.isBlank() -> R.string.error_email_required
                !PatternsCompat.EMAIL_ADDRESS.matcher(currentState.email)
                    .matches() -> R.string.error_incorrect_email_format

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
            @StringRes val message: Int? = when {
                currentState.password.isBlank() -> R.string.error_password_required
                currentState.password.length < 8 -> R.string.error_password_too_short
                else -> null
            }
            currentState.copy(
                passwordError = message
            )
        }
    }

    fun signIn(): Boolean {
        validateEmail()
        validatePassword()

        return (_signInState.value.emailError == null && _signInState.value.passwordError == null)
    }
}