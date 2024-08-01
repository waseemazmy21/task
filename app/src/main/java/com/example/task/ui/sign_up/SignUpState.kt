package com.example.task.ui.sign_up

import android.icu.util.Currency

data class SignUpState (
    val salutation: String = "",
    val salutationError: String? = null,
    val firstName: String = "",
    val firstNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val phoneNumberCode: String = SignUpViewModel.phoneNumberCodes[0],
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val currency: String = "",
    val currencyError: String? = null
)