package com.example.task.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    @StringRes errorMessage: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Password
    ),
    singleLine: Boolean = true,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = {
                Text(text = stringResource(label))
            },
            singleLine = singleLine,
            leadingIcon = {
                Icon(imageVector = leadingIcon, contentDescription = null)
            },
            trailingIcon = {
                if (isPasswordVisible) {
                    IconButton(onClick = { togglePasswordVisibility() }) {
                        Icon(imageVector = Icons.Outlined.VisibilityOff, "Hide Password")
                    }
                } else {
                    IconButton(onClick = { togglePasswordVisibility() }) {
                        Icon(imageVector = Icons.Outlined.Visibility, "Show Password")
                    }
                }
            },
            keyboardOptions = keyboardOptions,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = errorMessage != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (errorMessage != null) {
            Text(
                text = stringResource(errorMessage),
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}