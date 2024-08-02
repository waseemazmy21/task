package com.example.task.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    @StringRes errorMessage: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text
    ),
    singleLine: Boolean = true,
) {
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
            keyboardOptions = keyboardOptions,
            isError = errorMessage != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (errorMessage != null) {
            Text(
                text = stringResource(id = errorMessage),
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}