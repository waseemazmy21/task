package com.example.task.ui.sign_in

import CustomDropDownMenu
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.isPopupLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task.R
import com.example.task.ui.sign_up.SignUpViewModel
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.task.ui.components.CustomOutlinedTextField
import com.example.task.ui.components.PasswordOutlinedTextField
import kotlin.math.sign

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
            label = R.string.saluatation,
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
            Text(text = "Already have an account?")
            Text(
                text = "Sign in",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onSignInClick() }
            )
        }


    }
}


