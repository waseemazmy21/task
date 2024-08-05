package com.example.task.ui

import com.example.task.R
import com.example.task.ui.sign_in.SignInViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SignInUnitTest {
    private val signInViewModel = SignInViewModel()

    @Test
    fun signInViewModel_updateEmailWithValidEmail_emailErrorIsNull() {
        val validEmail = "waseem@gmail.com"
        signInViewModel.updateEmail(validEmail)
        val state = signInViewModel.signInState.value
        assertEquals(validEmail, state.email)
        assertNull(state.emailError)
    }


    @Test
    fun signInViewModel_updateEmailWithEmptyString_emailErrorSet() {
        val emptyEmail = ""
        signInViewModel.updateEmail(emptyEmail)
        val state = signInViewModel.signInState.value
        assertEquals(emptyEmail, state.email)
        assertEquals(R.string.error_email_required, state.emailError)
    }

    @Test
    fun signInViewModel_updateEmailWithWrongFormat_emailErrorSet() {
        val invalidEmail = "lol@lol"
        signInViewModel.updateEmail(invalidEmail)
        val state = signInViewModel.signInState.value
        assertEquals(invalidEmail, state.email)
        assertEquals(R.string.error_incorrect_email_format, state.emailError)
    }

    @Test
    fun signInViewModel_updatePasswordWithValidFormat_passwordErrorIsNUll() {
        val validPassword = "12345678"
        signInViewModel.updatePassword(validPassword)
        val state = signInViewModel.signInState.value
        assertEquals(validPassword, state.password)
        assertNull(state.passwordError)
    }

    @Test
    fun signInViewModel_updatePasswordWithEmptyPassword_passwordErrorSet() {
        val emptyPassword = ""
        signInViewModel.updatePassword(emptyPassword)
        val state = signInViewModel.signInState.value
        assertEquals(emptyPassword, state.password)
        assertEquals(R.string.error_password_required, state.passwordError)
    }

    @Test
    fun signInViewModel_updatePasswordWithInvalidPassword_passwordErrorSet() {
        val invalidPassword = "1234"
        signInViewModel.updatePassword(invalidPassword)
        val state = signInViewModel.signInState.value
        assertEquals(invalidPassword, state.password)
        assertEquals(R.string.error_password_too_short, state.passwordError)
    }


    @Test
    fun signInViewModel_SignInWithValidEmailAndValidPassword_emailErrorAndPasswordErrorAreNull() {
        val validEmail = "waseem@gmail.com"
        val validPassword = "12345678"
        signInViewModel.updateEmail(validEmail)
        signInViewModel.updatePassword(validPassword)
        assertTrue(signInViewModel.signIn())
    }

    @Test
    fun signInViewModel_SignInWithInvalidEmailAndValidPassword_emailErrorSet() {
        val invalidEmail = "abc"
        val validPassword = "12345678"
        signInViewModel.updateEmail(invalidEmail)
        signInViewModel.updatePassword(validPassword)
        assertFalse(signInViewModel.signIn())
    }

    @Test
    fun signInViewModel_SignInWithValidEmailAndInvalidPassword_passwordErrorSet() {
        val validEmail = "waseem@gmail.com"
        val invalidPassword = "aaa"
        signInViewModel.updateEmail(validEmail)
        signInViewModel.updatePassword(invalidPassword)
        assertFalse(signInViewModel.signIn())
    }

    @Test
    fun signInViewModel_SignInWithInvalidEmailAndInvalidPassword_emailErrorAndPasswordErrorSet() {
        val invalidEmail = "abc"
        val invalidPassword = "jjj"
        signInViewModel.updateEmail(invalidEmail)
        signInViewModel.updatePassword(invalidPassword)
        assertFalse(signInViewModel.signIn())
    }
}