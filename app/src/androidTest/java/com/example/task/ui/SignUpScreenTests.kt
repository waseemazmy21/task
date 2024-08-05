package com.example.task.ui

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.example.compose.TaskTheme
import com.example.task.App
import com.example.task.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTests {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        composeTestRule.onNodeWithText(context.resources.getString(R.string.sign_up)).performClick()
    }

    @Test
    fun signUpScreen_inputValidFirstName_firstNameErrorNotDisplayed() {
        val validFirstName = "Waseem"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.first_name))
            .performTextInput(validFirstName)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_first_name_required))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_first_name_too_short))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_first_name_too_long))
            .assertDoesNotExist()
    }

    @Test
    fun signUpScreen_inputEmptyFirstName_firstNameErrorDisplayed() {
        composeTestRule.onNodeWithText(context.resources.getString(R.string.first_name))
            .performTextInput("anything")
        composeTestRule.onNodeWithText(context.resources.getString(R.string.first_name))
            .performTextClearance()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_first_name_required))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputTooShortFirstName_firstNameErrorDisplayed() {
        val tooShortFirstName = "ab"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.first_name))
            .performTextInput(tooShortFirstName)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_first_name_too_short))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputTooLongFirstName_firstNameErrorDisplayed() {
        val tooLongFirstName = "ThisIsAVeryLongFirstNameThatExceedsTheMaximumAllowedLength"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.first_name))
            .performTextInput(tooLongFirstName)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_first_name_too_long))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputValidLastName_lastNameErrorNotDisplayed() {
        val validLastName = "Azmy"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.last_name))
            .performTextInput(validLastName)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_last_name_required))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_last_name_too_short))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_last_name_too_long))
            .assertDoesNotExist()
    }

    @Test
    fun signUpScreen_inputEmptyLastName_lastNameErrorDisplayed() {
        composeTestRule.onNodeWithText(context.resources.getString(R.string.last_name))
            .performTextInput("anything")
        composeTestRule.onNodeWithText(context.resources.getString(R.string.last_name))
            .performTextClearance()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_last_name_required))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputTooShortLastName_lastNameErrorDisplayed() {
        val tooShortLastName = "ab"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.last_name))
            .performTextInput(tooShortLastName)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_last_name_too_short))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputTooLongLastName_lastNameErrorDisplayed() {
        val tooLongLastName = "ThisIsAVeryLongLastNameThatExceedsTheMaximumAllowedLength"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.last_name))
            .performTextInput(tooLongLastName)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_last_name_too_long))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputValidEmail_emailErrorNotDisplayed() {
        val validEmail = "waseem@gmail.com"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextInput(validEmail)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_email_required))
            .assertDoesNotExist()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_incorrect_email_format))
            .assertDoesNotExist()
    }

    @Test
    fun signUpScreen_inputEmptyEmail_emailErrorDisplayed() {
        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextInput("anything")
        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextClearance()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_email_required))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputInvalidEmail_emailErrorDisplayed() {
        val invalidEmail = "waseem@gmail"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextInput(invalidEmail)
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_incorrect_email_format))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputEmptyPhoneNumber_phoneNumberErrorDisplayed() {
        composeTestRule.onNodeWithText(context.resources.getString(R.string.phone))
            .performTextInput("123")
        composeTestRule.onNodeWithText(context.resources.getString(R.string.phone))
            .performTextClearance()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_phone_number_required))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputInValidEgyptianPhoneNumberAndCode_phoneNumberErrorDisplayed() {
        val egyptCountryCode = "+20"
        val invalidEgyptianNumber = "3453"

        composeTestRule.onNodeWithText(context.getString(R.string.code))
            .performClick()
        composeTestRule.onNodeWithTag(egyptCountryCode).performClick()

        composeTestRule.onNodeWithText(context.getString(R.string.phone))
            .performTextInput(invalidEgyptianNumber)

        composeTestRule.onNodeWithText(context.getString(R.string.error_incorrect_phone_format))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputValidEgyptianPhoneNumberAndCode_phoneNumberErrorNotDisplayed() {
        val egyptCountryCode = "+20"
        val validEgyptianNumber = "1557119734"

        composeTestRule.onNodeWithText(context.getString(R.string.code))
            .performClick()
        composeTestRule.onNodeWithTag(egyptCountryCode).performClick()

        composeTestRule.onNodeWithText(context.getString(R.string.phone))
            .performTextInput(validEgyptianNumber)

        composeTestRule.onNodeWithText(context.getString(R.string.error_incorrect_phone_format))
            .assertDoesNotExist()
    }

    @Test
    fun signUpScreen_inputValidUkPhoneNumberAndCode_phoneNumberErrorNotDisplayed() {
        val ukCode = "+44"
        val validUkNumber = "07123456789"

        composeTestRule.onNodeWithText(context.getString(R.string.code))
            .performClick()
        composeTestRule.onNodeWithTag(ukCode).performClick()

        composeTestRule.onNodeWithText(context.getString(R.string.phone))
            .performTextInput(validUkNumber)

        composeTestRule.onNodeWithText(context.getString(R.string.error_incorrect_phone_format))
            .assertDoesNotExist()
    }

    @Test
    fun signUpScreen_inputInvalidUkPhoneNumberAndCode_phoneNumberErrorNotDisplayed() {
        val ukCode = "+44"
        val invalidUkNumber = "971456789"

        composeTestRule.onNodeWithText(context.getString(R.string.code))
            .performClick()
        composeTestRule.onNodeWithTag(ukCode).performClick()

        composeTestRule.onNodeWithText(context.getString(R.string.phone))
            .performTextInput(invalidUkNumber)

        composeTestRule.onNodeWithText(context.getString(R.string.error_incorrect_phone_format))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputEmptyPassword_passwordErrorDisplayed() {
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput("anything")
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextClearance()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_required))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputTooShortPassword_passwordErrorDisplayed() {
        val tooShortPassword = "1234"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput(tooShortPassword)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_too_short))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputValidPassword_passwordErrorNotDisplayed() {
        val validPassword = "12345678"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput(validPassword)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_required))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_too_short))
            .assertDoesNotExist()
    }

    @Test
    fun signUpScreen_inputEmptyConfirmPassword_confirmPasswordErrorDisplayed() {
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput("12345678")

        composeTestRule.onNodeWithText(context.resources.getString(R.string.confirm_password))
            .performTextInput("anything")
        composeTestRule.onNodeWithText(context.resources.getString(R.string.confirm_password))
            .performTextClearance()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_confirm_password_required))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputNonMatchingConfirmPassword_confirmPasswordErrorDisplayed() {
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput("12345678")

        val nonMatchingConfirmPassword = "abc"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.confirm_password))
            .performTextInput(nonMatchingConfirmPassword)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_confirmation_passwords_do_not_match_password))
            .assertExists()
    }

    @Test
    fun signUpScreen_inputMatchingConfirmPassword_confirmPasswordErrorNotDisplayed() {
        val password = "12345678"
        val matchingConfirmPassword = "12345678"

        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput(password)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.confirm_password))
            .performTextInput(matchingConfirmPassword)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_required))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_confirmation_passwords_do_not_match_password))
            .assertDoesNotExist()
    }

}