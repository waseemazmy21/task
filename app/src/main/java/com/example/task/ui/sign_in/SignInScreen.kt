package com.example.task.ui.sign_in

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task.R
import com.example.task.ui.components.CustomOutlinedTextField
import com.example.task.ui.components.PasswordOutlinedTextField

@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel = viewModel(),
) {
    val signInState by signInViewModel.signInState.collectAsState()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 80.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        CustomOutlinedTextField(
            value = signInState.email,
            onValueChange = { it: String -> signInViewModel.updateEmail(it) },
            label = R.string.email,
            leadingIcon = Icons.Outlined.Person,
            errorMessage = signInState.emailError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        PasswordOutlinedTextField(
            value = signInState.password,
            onValueChange = { it: String -> signInViewModel.updatePassword(it) },
            label = R.string.password,
            leadingIcon = Icons.Outlined.Lock,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            errorMessage = signInState.passwordError,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Forgot your password?",
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.align(Alignment.End),
        )

        Spacer(modifier = Modifier.height(80.dp))

        Buttons(
            onSignIn = { signInViewModel.signIn() },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(text = stringResource(R.string.do_not_have_an_account))
            Text(
                text = stringResource(id = R.string.sign_up),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }
    }
}


@Composable
fun Buttons(
    onSignIn: () -> Boolean,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
        val context = LocalContext.current

        Button(onClick = {
            if (onSignIn()) {
                Toast.makeText(
                    context,
                    context.getString(R.string.sing_in_successfully), Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.please_fix_the_errors_and_try_again),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.sign_in).uppercase(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(12.dp)
            )
        }

        FilledTonalButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.continue_as_a_guest).uppercase(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}