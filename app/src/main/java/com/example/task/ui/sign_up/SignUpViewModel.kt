package com.example.task.ui.sign_up

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.task.ui.utils.EMAIL_REGEX
import com.example.task.ui.utils.phone_regex


class SignUpViewModel: ViewModel() {
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()

    fun updateSalutation(updatedSalutation: String) {
        _signUpState.update { currentState ->
            currentState.copy(salutation = updatedSalutation)
        }

        validateSalutation()
    }

    private fun validateSalutation() {
        _signUpState.update { currentState ->
            val message: String? = when {
                currentState.salutation.isBlank() -> "salutation field is required"
                else -> null
            }
            currentState.copy(
                salutationError = message
            )
        }
    }

    fun updateFirstName(updatedFirstName: String) {
        _signUpState.update { currentState ->
            currentState.copy(firstName = updatedFirstName)
        }

        validateFirstName()
    }

    private fun validateFirstName() {
        _signUpState.update { currentState ->
            val message: String? = when {
                currentState.firstName.isBlank() -> "first name field is required"
                currentState.firstName.length < 3 -> "first name must be at least 3 characters"
                currentState.firstName.length > 15 -> "first name must be at most 15 characters"
                else -> null
            }
            currentState.copy(
                firstNameError = message
            )
        }
    }

    fun updateLastName(updatedLastName: String) {
        _signUpState.update { currentState ->
            currentState.copy(lastName = updatedLastName)
        }

        validateLastName()
    }

    private fun validateLastName() {
        _signUpState.update { currentState ->
            val message: String? = when {
                currentState.lastName.isBlank() -> "last name field is required"
                currentState.lastName.length < 3 -> "last name must be at least 3 characters"
                currentState.lastName.length > 15 -> "last name must be at most 15 characters"
                else -> null
            }
            currentState.copy(
                lastNameError = message
            )
        }
    }

    fun updateEmail(updatedEmail: String) {
        _signUpState.update { currentState ->
            currentState.copy(
                email = updatedEmail,
            )
        }

        validateEmail()
    }

    private fun validateEmail() {
        _signUpState.update { currentState ->
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
        _signUpState.update { currentState ->
            currentState.copy(
                password = updatedPassword,
            )
        }

        validatePassword()
    }

    private fun validatePassword() {
        _signUpState.update { currentState ->
            val message: String? = when {
                currentState.password.isBlank() -> "password field is required"
                currentState.password.length < 8 -> "password must be at least 3 characters"
                else -> null
            }
            currentState.copy(
                passwordError = message
            )
        }
    }

    fun updateConfirmPassword(updatedConfirmPassword: String) {
        _signUpState.update { currentState ->
            currentState.copy(
                confirmPassword = updatedConfirmPassword,
            )
        }
    }

    private fun validateConfirmationPassword() {
        _signUpState.update { currentState ->
            val message: String? = when {
                currentState.confirmPassword.isBlank() -> "confirmation password field is required"
                currentState.confirmPasswordError != currentState.password -> "confirmation password doesn't match password"
                else -> null
            }
            currentState.copy(
                confirmPasswordError = message
            )
        }
    }

    fun updatePhoneNumber(updatedPhoneNumber: String) {
        _signUpState.update { currentState ->
            currentState.copy(
                phoneNumber = updatedPhoneNumber
            )
        }
        validatePhoneNumber()
    }


    fun updatePhoneNumberCode(updatedCode: String) {
        _signUpState.update { currentState ->
            currentState.copy(phoneNumberCode = updatedCode)
        }
        validatePhoneNumber()
    }

    private fun validatePhoneNumber() {
        _signUpState.update { currentState ->
            val message: String? = when {
                currentState.phoneNumberCode.isBlank() -> "choose a phone code"
                currentState.phoneNumber.isBlank() -> "phone number field is required"
                !currentState.phoneNumber.matches(phone_regex[currentState.phoneNumberCode]!!.toRegex()) -> "wrong phone format"
                else -> null
            }
            currentState.copy(
                phoneNumberError = message
            )
        }
    }

    fun updateCurrency(updatedCurrency: String) {
        _signUpState.update { currentState ->
            currentState.copy(currency = updatedCurrency)
        }
    }

    private fun validateCurrency() {
        _signUpState.update { currentState ->
            val message: String? = when {
                currentState.phoneNumber.isBlank() -> "currency field is required"
                else -> null
            }
            currentState.copy(
                currencyError = message
            )
        }
    }

    fun signup() {
        validateSalutation()
        validateFirstName()
        validateLastName()
        validateEmail()
        validatePhoneNumber()
        validatePassword()
        validateConfirmationPassword()
        validateCurrency()
    }

    companion object {
        val salutationOptions = listOf(
            "Mr.",
            "Mrs.",
            "Ms.",
        )

        val phoneNumberCodes = listOf(
            "+20",
            "+44"
        )

        val currencies = listOf(
            "EGP",
            "USD",
            "CAD"

        )
    }
}
