package com.example.task.ui

import com.example.task.R
import com.example.task.ui.sign_up.SignUpViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test


class SignUpUnitTest {
    private val signUpViewModel = SignUpViewModel()

    @Test
    fun signUpViewModel_updateFirstNameWithValidFirstName_firstNameErrorIsNull() {
        val validFirstName = "waseem"
        signUpViewModel.updateFirstName(validFirstName)
        val state = signUpViewModel.signUpState.value
        assertEquals(validFirstName, state.firstName)
        assertNull(state.firstNameError)
    }

    @Test
    fun signUpViewModel_updateFirstNameWithEmptyFirstName_firstNameErrorSet() {
        val emptyFirstName = ""
        signUpViewModel.updateFirstName(emptyFirstName)
        val state = signUpViewModel.signUpState.value
        assertEquals(emptyFirstName, state.firstName)
        assertEquals(R.string.error_first_name_required, state.firstNameError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updateFirstNameWithTooShortFirstName_firstNameErrorSet() {
        val tooShortFirstName = "ab"
        signUpViewModel.updateFirstName(tooShortFirstName)
        val state = signUpViewModel.signUpState.value
        assertEquals(tooShortFirstName, state.firstName)
        assertEquals(R.string.error_first_name_too_short, state.firstNameError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updateFirstNameWithTooLongFirstName_firstNameErrorSet() {
        val tooLongFirstName = "ThisIsAVeryLongFirstNameThatExceedsTheMaximumAllowedLength"
        signUpViewModel.updateFirstName(tooLongFirstName)
        val state = signUpViewModel.signUpState.value
        assertEquals(tooLongFirstName, state.firstName)
        assertEquals(R.string.error_first_name_too_long, state.firstNameError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updateLastNameWithValidLastName_lastNameErrorIsNull() {
        val validLastName = "waseem"
        signUpViewModel.updateLastName(validLastName)
        val state = signUpViewModel.signUpState.value
        assertEquals(validLastName, state.lastName)
        assertNull(state.lastNameError)
    }

    @Test
    fun signUpViewModel_updateLastNameWithEmptyLastName_lastNameErrorSet() {
        val emptyLastName = ""
        signUpViewModel.updateLastName(emptyLastName)
        val state = signUpViewModel.signUpState.value
        assertEquals(emptyLastName, state.lastName)
        assertEquals(R.string.error_last_name_required, state.lastNameError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updateLastNameWithTooShortLastName_lastNameErrorSet() {
        val tooShortLastName = "ab"
        signUpViewModel.updateLastName(tooShortLastName)
        val state = signUpViewModel.signUpState.value
        assertEquals(tooShortLastName, state.lastName)
        assertEquals(R.string.error_last_name_too_short, state.lastNameError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updateLastNameWithTooLongLastName_lastNameErrorSet() {
        val tooLongLastName = "ThisIsAVeryLongLastNameThatExceedsTheMaximumAllowedLength"
        signUpViewModel.updateLastName(tooLongLastName)
        val state = signUpViewModel.signUpState.value
        assertEquals(tooLongLastName, state.lastName)
        assertEquals(R.string.error_last_name_too_long, state.lastNameError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updatePhoneNumberWithEmptyPhoneNumber_phoneNumberErrorSet() {
        val emptyPhoneNumber = ""
        signUpViewModel.updatePhoneNumber(emptyPhoneNumber)
        val state = signUpViewModel.signUpState.value
        assertEquals(emptyPhoneNumber, state.phoneNumber)
        assertEquals(R.string.error_phone_number_required, state.phoneNumberError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updatePhoneNumberWithValidEgyptianNumber_phoneNumberErrorIsNull() {
        val egyptCountryCode = "+20"
        val validEgyptianNumber = "1557119734"

        signUpViewModel.updatePhoneNumberCode(egyptCountryCode)
        signUpViewModel.updatePhoneNumber(validEgyptianNumber)

        val state = signUpViewModel.signUpState.value
        assertEquals(egyptCountryCode, state.phoneNumberCode)
        assertEquals(validEgyptianNumber, state.phoneNumber)
        assertNull(state.phoneNumberError)
    }

    @Test
    fun signUpViewModel_updatePhoneNumberWithInvalidFormat_phoneNumberErrorSet() {
        val egyptCountryCode = "+20"
        val invalidPhoneNumber = "12345"

        signUpViewModel.updatePhoneNumberCode(egyptCountryCode)
        signUpViewModel.updatePhoneNumber(invalidPhoneNumber)

        val state = signUpViewModel.signUpState.value
        assertEquals(invalidPhoneNumber, state.phoneNumber)
        assertEquals(egyptCountryCode, state.phoneNumberCode)
        assertEquals(R.string.error_incorrect_phone_format, state.phoneNumberError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updatePhoneNumberWithValidUkNumber_phoneNumberErrorIsNull() {
        val ukCountryCode = "+44"
        val validUkNumber = "07123456789"

        signUpViewModel.updatePhoneNumberCode(ukCountryCode)
        signUpViewModel.updatePhoneNumber(validUkNumber)

        val state = signUpViewModel.signUpState.value
        assertEquals(ukCountryCode, state.phoneNumberCode)
        assertEquals(validUkNumber, state.phoneNumber)
        assertNull(state.phoneNumberError)
    }

    @Test
    fun signUpViewModel_updatePhoneNumberWithInvalidUkFormat_phoneNumberErrorSet() {
        val ukCountryCode = "+44"
        val invalidUkNumber = "12345"

        signUpViewModel.updatePhoneNumberCode(ukCountryCode)
        signUpViewModel.updatePhoneNumber(invalidUkNumber)

        val state = signUpViewModel.signUpState.value
        assertEquals(invalidUkNumber, state.phoneNumber)
        assertEquals(ukCountryCode, state.phoneNumberCode)
        assertEquals(R.string.error_incorrect_phone_format, state.phoneNumberError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updateEmailWithValidEmail_emailErrorIsNull() {
        val validEmail = "waseem@gmail.com"
        signUpViewModel.updateEmail(validEmail)
        val state = signUpViewModel.signUpState.value
        assertEquals(validEmail, state.email)
        assertNull(state.emailError)
    }

    @Test
    fun signUpViewModel_updateEmailWithEmptyString_emailErrorSet() {
        val emptyEmail = ""
        signUpViewModel.updateEmail(emptyEmail)
        val state = signUpViewModel.signUpState.value
        assertEquals(emptyEmail, state.email)
        assertEquals(R.string.error_email_required, state.emailError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updateEmailWithWrongFormat_emailErrorSet() {
        val invalidEmail = "lol@lol"
        signUpViewModel.updateEmail(invalidEmail)
        val state = signUpViewModel.signUpState.value
        assertEquals(invalidEmail, state.email)
        assertEquals(R.string.error_incorrect_email_format, state.emailError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updatePasswordWithValidFormat_passwordErrorIsNUll() {
        val validPassword = "12345678"
        signUpViewModel.updatePassword(validPassword)
        val state = signUpViewModel.signUpState.value
        assertEquals(validPassword, state.password)
        assertNull(state.passwordError)
    }

    @Test
    fun signUpViewModel_updatePasswordWithEmptyPassword_passwordErrorSet() {
        val emptyPassword = ""
        signUpViewModel.updatePassword(emptyPassword)
        val state = signUpViewModel.signUpState.value
        assertEquals(emptyPassword, state.password)
        assertEquals(R.string.error_password_required, state.passwordError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updatePasswordWithInvalidPassword_passwordErrorSet() {
        val invalidPassword = "1234"
        signUpViewModel.updatePassword(invalidPassword)
        val state = signUpViewModel.signUpState.value
        assertEquals(invalidPassword, state.password)
        assertEquals(R.string.error_password_too_short, state.passwordError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updateConfirmPasswordWithMatchingPassword_confirmPasswordErrorIsNull() {
        val validPassword = "12345678"
        val matchingConfirmPassword = "12345678"

        signUpViewModel.updatePassword(validPassword)
        signUpViewModel.updateConfirmPassword(matchingConfirmPassword)

        val state = signUpViewModel.signUpState.value
        assertEquals(validPassword, state.password)
        assertEquals(matchingConfirmPassword, state.confirmPassword)
        assertNull(state.confirmPasswordError)
    }

    @Test
    fun signUpViewModel_updateConfirmPasswordWithEmptyConfirmPassword_confirmPasswordErrorSet() {
        val password = "somepassword"
        val emptyConfirmPassword = ""

        signUpViewModel.updatePassword(password)
        signUpViewModel.updateConfirmPassword(emptyConfirmPassword)
        val state = signUpViewModel.signUpState.value

        assertEquals(password, state.password)
        assertEquals(emptyConfirmPassword, state.confirmPassword)
        assertEquals(R.string.error_confirm_password_required, state.confirmPasswordError)
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_updateConfirmPasswordWithNonMatchingPassword_confirmPasswordErrorSet() {
        val password = "12345678"
        val nonMatchingConfirmPassword = "87654321"

        signUpViewModel.updatePassword(password)
        signUpViewModel.updateConfirmPassword(nonMatchingConfirmPassword)
        val state = signUpViewModel.signUpState.value

        assertEquals(password, state.password)
        assertEquals(nonMatchingConfirmPassword, state.confirmPassword)
        assertEquals(
            R.string.error_confirmation_passwords_do_not_match_password,
            state.confirmPasswordError
        )
        assertFalse(signUpViewModel.signUp())
    }

    @Test
    fun signUpViewModel_signUpWithValidData_returnsTrue() {
        signUpViewModel.updateFirstName("Waseem")
        signUpViewModel.updateLastName("Azmy")
        signUpViewModel.updateEmail("waseem@gmail.com")
        signUpViewModel.updatePhoneNumberCode("+20") // Assuming Egypt code
        signUpViewModel.updatePhoneNumber("1557119734")
        signUpViewModel.updatePassword("12345678")
        signUpViewModel.updateConfirmPassword("12345678")

        assertTrue(signUpViewModel.signUp())
    }

}