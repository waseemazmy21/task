package com.example.task.ui.sign_in

import CustomDropDownMenu
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task.R
import com.example.task.ui.components.CustomOutlinedTextField
import com.example.task.ui.components.PasswordOutlinedTextField
import com.example.task.ui.sign_up.SignUpViewModel

@Composable
fun SignUpScreen(
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpViewModel = viewModel(),
) {
    val signUpState by signUpViewModel.signUpState.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 18.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {

        CustomDropDownMenu(
            selectedOption = signUpState.salutation,
            onSelectedOptionChange = { signUpViewModel.updateSalutation(it) },
            options = SignUpViewModel.salutationOptions,
            errorMessage = signUpState.salutationError,
            label = R.string.salutation,
            leadingIcon = Icons.Outlined.Forum,
            modifier = Modifier.fillMaxWidth()
        )

        CustomOutlinedTextField(
            value = signUpState.firstName,
            onValueChange = { signUpViewModel.updateFirstName(it) },
            label = R.string.first_name,
            errorMessage = signUpState.firstNameError,
            leadingIcon = Icons.Outlined.Person,
            modifier = Modifier.fillMaxWidth()
        )

        CustomOutlinedTextField(
            value = signUpState.lastName,
            onValueChange = { signUpViewModel.updateLastName(it) },
            label = R.string.last_name,
            errorMessage = signUpState.lastNameError,
            leadingIcon = Icons.Outlined.Person,
            modifier = Modifier.fillMaxWidth()
        )


        CustomOutlinedTextField(
            value = signUpState.email,
            onValueChange = { signUpViewModel.updateEmail(it) },
            label = R.string.email,
            errorMessage = signUpState.emailError,
            leadingIcon = Icons.Outlined.Person,
            modifier = Modifier.fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            CustomDropDownMenu(
                selectedOption = signUpState.phoneNumberCode,
                onSelectedOptionChange = { signUpViewModel.updatePhoneNumberCode(it) },
                options = SignUpViewModel.phoneNumberCodes,
                leadingIcon = Icons.Outlined.Phone,
                modifier = Modifier.width(140.dp)
            )

            CustomOutlinedTextField(
                value = signUpState.phoneNumber,
                onValueChange = { signUpViewModel.updatePhoneNumber(it) },
                label = R.string.phone,
                errorMessage = signUpState.phoneNumberError,
                leadingIcon = Icons.Outlined.Phone,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.weight(1f)
            )
        }

        PasswordOutlinedTextField(
            value = signUpState.password,
            onValueChange = { signUpViewModel.updatePassword(it) },
            label = R.string.password,
            leadingIcon = Icons.Outlined.Lock,
            errorMessage = signUpState.passwordError,
            modifier = Modifier.fillMaxWidth()
        )

        PasswordOutlinedTextField(
            value = signUpState.confirmPassword,
            onValueChange = { signUpViewModel.updateConfirmPassword(it) },
            label = R.string.confirm_password,
            leadingIcon = Icons.Outlined.Lock,
            errorMessage = signUpState.confirmPasswordError,
            modifier = Modifier.fillMaxWidth()
        )

        CustomDropDownMenu(
            selectedOption = signUpState.currency,
            onSelectedOptionChange = { signUpViewModel.updateCurrency(it) },
            options = SignUpViewModel.currencies,
            errorMessage = signUpState.currencyError,
            label = R.string.currency,
            leadingIcon = Icons.Outlined.Money,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                signUpViewModel.signup()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.sign_up).uppercase(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(12.dp)
            )
        }

        Row {
            Text(text = stringResource(R.string.already_have_an_account))
            Text(
                text = stringResource(id = R.string.sign_in),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onSignInClick() }
            )
        }


    }
}


