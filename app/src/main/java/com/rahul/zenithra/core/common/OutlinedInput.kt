package com.rahul.zenithra.core.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rahul.zenithra.R
import com.rahul.zenithra.ui.theme.CustomTheme
import com.rahul.zenithra.ui.theme.Icon
import com.rahul.zenithra.ui.theme.OutlineBorder

@Composable
fun OutLinedInput(
    label: String,
    value: String,
    prefix: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    maxLength: Int? = null,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    regEx: String = "[\\p{So}]",
    placeholder: String? = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions(),
    isSingleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    labelStyle: TextStyle = MaterialTheme.typography.labelSmall.copy(color = CustomTheme.colors.secondaryText),
    labelColor: Color = CustomTheme.colors.secondaryText,
    placeholderStyle: TextStyle = TextStyle(
        fontStyle = FontStyle.Italic,
        color = CustomTheme.colors.placeholderText
    ),
    textStyle: TextStyle = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        color = CustomTheme.colors.primaryText
    ),
    unfocusedBorderColor: Color = CustomTheme.colors.whiteContentBackground,
    isError: Boolean = false,
) {
    val emojiRegex = Regex(regEx)
    var passwordVisible by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.End) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            readOnly = readOnly,
            enabled = enabled,
            prefix = prefix,
            placeholder = {
                Text(
                    text = placeholder ?: "",
                    style = placeholderStyle,
                )
            },
            trailingIcon = if (keyboardType == KeyboardType.Password) {
                {
                    Icon(
                        painter = painterResource(id = if (passwordVisible) Icon.ic_eye_hide else Icon.ic_eye),
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        modifier = Modifier
                            .size(18.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null // Disable ripple
                            ) {
                                passwordVisible = !passwordVisible
                            },
                        tint = CustomTheme.colors.primaryIcon,
                    )
                }
            } else trailingIcon,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,                // White when focused
                unfocusedBorderColor = OutlineBorder,              // Black when not focused
                focusedLabelColor = Color.White,                 // White label when focused
                unfocusedLabelColor = Color.White,               // White label when not focused
                disabledBorderColor = Color.Gray,                // Optional
                cursorColor = Color.White                        // Optional for white cursor
            ),
            value = value,
            minLines = minLines,
            maxLines = if (isSingleLine) 1 else maxLines,
            singleLine = isSingleLine,
            textStyle = textStyle,
            label = {
                Text(
                    text = label,
                    style = labelStyle,
                    color = labelColor,
                )
            },
            onValueChange = {
                if (maxLength != null) onValueChange(it.replace(emojiRegex, "").take(maxLength))
                else onValueChange(it.replace(emojiRegex, ""))
            },
            keyboardOptions =
            KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            keyboardActions = keyboardActions,
            visualTransformation = if (keyboardType == KeyboardType.Password && !passwordVisible)
                PasswordVisualTransformation()
            else
                visualTransformation,
        )
        if (isError) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(id = R.string.required),
                style = MaterialTheme.typography.bodySmall,
                color = CustomTheme.colors.danger,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutLinedInputPreview() {
    OutLinedInput(label = stringResource(R.string.email), value = "", onValueChange = {})
}