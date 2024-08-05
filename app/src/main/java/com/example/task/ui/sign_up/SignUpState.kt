package com.example.task.ui.sign_up

import androidx.annotation.StringRes

data class SignUpState(
    val salutation: String = SignUpViewModel.salutationOptions[0],
    @StringRes val salutationError: Int? = null,
    val firstName: String = "",
    @StringRes val firstNameError: Int? = null,
    val lastName: String = "",
    @StringRes val lastNameError: Int? = null,
    val email: String = "",
    @StringRes val emailError: Int? = null,
    val phoneNumberCode: String = SignUpViewModel.phoneNumberCodes[0],
    val phoneNumber: String = "",
    @StringRes val phoneNumberError: Int? = null,
    val password: String = "",
    @StringRes val passwordError: Int? = null,
    val confirmPassword: String = "",
    @StringRes val confirmPasswordError: Int? = null,
    val currency: String = SignUpViewModel.currencies[0],
    @StringRes val currencyError: Int? = null
)