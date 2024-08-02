package com.example.task.ui.sign_in

import androidx.annotation.StringRes

data class SignInState(
    val email: String = "",
    @StringRes val emailError: Int? = null,
    val password: String = "",
    @StringRes val passwordError: Int? = null
)
