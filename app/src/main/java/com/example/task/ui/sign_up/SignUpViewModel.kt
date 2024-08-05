package com.example.task.ui.sign_up

import androidx.annotation.StringRes
import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import com.example.task.R
import com.example.task.ui.utils.phone_regex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignUpViewModel : ViewModel() {
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
            @StringRes val message: Int? = when {
                currentState.salutation.isBlank() -> R.string.error_salutation_required
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
            @StringRes val message: Int? = when {
                currentState.firstName.isBlank() -> R.string.error_first_name_required
                currentState.firstName.length < 3 -> R.string.error_first_name_too_short
                currentState.firstName.length > 15 -> R.string.error_first_name_too_long
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
            @StringRes val message: Int? = when {
                currentState.lastName.isBlank() -> R.string.error_last_name_required
                currentState.lastName.length < 3 -> R.string.error_last_name_too_short
                currentState.lastName.length > 15 -> R.string.error_last_name_too_long
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
        _signUpState.update { currentState ->
            currentState.copy(
                password = updatedPassword,
            )
        }

        validatePassword()
    }

    private fun validatePassword() {
        _signUpState.update { currentState ->
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

    fun updateConfirmPassword(updatedConfirmPassword: String) {
        _signUpState.update { currentState ->
            currentState.copy(
                confirmPassword = updatedConfirmPassword,
            )
        }

        validateConfirmationPassword()
    }

    private fun validateConfirmationPassword() {
        _signUpState.update { currentState ->
            @StringRes val message: Int? = when {
                currentState.confirmPassword.isBlank() -> R.string.error_confirm_password_required
                currentState.confirmPassword != currentState.password -> R.string.error_confirmation_passwords_do_not_match_password
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
            @StringRes val message: Int? = when {
                currentState.phoneNumberCode.isBlank() -> R.string.error_phone_code_required
                currentState.phoneNumber.isBlank() -> R.string.error_phone_number_required
                !currentState.phoneNumber.matches(phone_regex[currentState.phoneNumberCode]!!.toRegex()) -> R.string.error_incorrect_phone_format
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
            @StringRes val message: Int? = when {
                currentState.currency.isBlank() -> R.string.error_currency_required
                else -> null
            }
            currentState.copy(
                currencyError = message
            )
        }
    }

    fun signUp(): Boolean {
        validateSalutation()
        validateFirstName()
        validateLastName()
        validateEmail()
        validatePhoneNumber()
        validatePassword()
        validateConfirmationPassword()
        validateCurrency()

        val state = signUpState.value

        return state.firstNameError == null &&
                state.lastNameError == null &&
                state.emailError == null &&
                state.phoneNumberError == null &&
                state.passwordError == null &&
                state.confirmPasswordError == null &&
                state.currencyError == null
    }

    companion object {
        val salutationOptions = listOf(
            "Mr.",
            "Mrs.",
            "Ms.",
        )

        val phoneNumberCodes = listOf(
            "+20", "+44"
        )

        val currencies = listOf(
            "EGP", "USD", "CAD"

        )
    }
}
