package com.example.task.ui

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.example.compose.TaskTheme
import com.example.task.App
import com.example.task.R
import org.junit.Rule
import org.junit.Test

class SignInScreenTests {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun signInScreen_inputValidEmail_emailErrorNotDisplayed() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        val validEmail = "waseem@gmail.com"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextInput(validEmail)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_email_required))
            .assertDoesNotExist()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_incorrect_email_format))
            .assertDoesNotExist()
    }

    @Test
    fun signInScreen_inputEmptyEmail_emailErrorDisplayed() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextInput("anything")
        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextClearance()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_email_required))
            .assertExists()
    }

    @Test
    fun signInScreen_inputInvalidEmail_emailErrorDisplayed() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        val invalidEmail = "waseem@gmail"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextInput(invalidEmail)
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_incorrect_email_format))
            .assertExists()
    }

    @Test
    fun signInScreen_inputValidPassword_passwordErrorNotDisplayed() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        val validPassword = "12345678"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput(validPassword)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_required))
            .assertDoesNotExist()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_too_short))
            .assertDoesNotExist()

    }

    @Test
    fun signInScreen_inputEmptyPassword_passwordErrorDisplayed() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput("anything")
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextClearance()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_required))
            .assertExists()
    }

    @Test
    fun signInScreen_inputInvalidPassword_passwordErrorDisplayed() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        val invalidPassword = "jjj"
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput(invalidPassword)
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_too_short))
            .assertExists()
    }

    @Test
    fun signInScreen_signInWithValidEmailAndValidPassword_emailAndPasswordErrorsNotDisplayed() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        val validEmail = "waseem@gmail.com"
        val validPassword = "12345678"

        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextInput(validEmail)
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput(validPassword)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_email_required))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_incorrect_email_format))
            .assertDoesNotExist()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_required))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_too_short))
            .assertDoesNotExist()

    }

    @Test
    fun signInScreen_signInWithInvalidEmailAndValidPassword_emailErrorDisplayed() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        val invalidEmail = "abc"
        val validPassword = "12345678"

        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextInput(invalidEmail)
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput(validPassword)


        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_incorrect_email_format))
            .assertExists()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_required))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_too_short))
            .assertDoesNotExist()
    }

    @Test
    fun signInScreen_signInWithValidEmailAndInvalidPassword_passwordErrorDisplayed() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        val validEmail = "waseem@gmail.com"
        val invalidPassword = "aaa"

        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextInput(validEmail)
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput(invalidPassword)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_email_required))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_incorrect_email_format))
            .assertDoesNotExist()

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_too_short))
            .assertExists()
    }

    @Test
    fun signInScreen_signInWithInvalidEmailAndInvalidPassword_EmailAndPasswordErrorsDisplayed() {
        composeTestRule.setContent {
            TaskTheme {
                App()
            }
        }

        val invalidEmail = "abc"
        val invalidPassword = "jjj"

        composeTestRule.onNodeWithText(context.resources.getString(R.string.email))
            .performTextInput(invalidEmail)
        composeTestRule.onNodeWithText(context.resources.getString(R.string.password))
            .performTextInput(invalidPassword)

        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_incorrect_email_format))
            .assertExists()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.error_password_too_short))
            .assertExists()
    }

}