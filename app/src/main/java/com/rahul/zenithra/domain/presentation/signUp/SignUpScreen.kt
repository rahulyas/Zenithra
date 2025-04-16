package com.rahul.zenithra.domain.presentation.signUp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rahul.zenithra.ui.theme.CardBackground
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.rahul.zenithra.R
import com.rahul.zenithra.core.common.OutLinedInput
import com.rahul.zenithra.domain.presentation.signUp.component.SignUpUiEvent
import com.rahul.zenithra.ui.theme.Icon
import com.rahul.zenithra.ui.theme.buttonBackground


@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    onSignInClick: () -> Unit
) {
    val viewModel = hiltViewModel<SignUpViewModel>()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is SignUpUiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Zenithra", color = Color.White, style = MaterialTheme.typography.titleMedium)
                Text("Create Account", color = Color.White, style = MaterialTheme.typography.headlineSmall)
                Text("Please enter your details to sign up", color = Color.White)

                Spacer(modifier = Modifier.height(16.dp))

                // Social buttons
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    IconButton(
                        onClick = { /* Google Sign Up */ },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(CardBackground)
                            .border(2.dp, Color.White, CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(id = Icon.google),
                            contentDescription = "Google Sign Up",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(
                        onClick = { /* Apple Sign Up */ },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(CardBackground)
                            .border(2.dp, Color.White, CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(id = Icon.apple),
                            contentDescription = "Apple Sign Up",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // OR Divider
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Gray)
                    Text("OR", modifier = Modifier.padding(horizontal = 8.dp), color = Color.White)
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Gray)
                }

                // Name Input
                OutLinedInput(
                    label = "Name",
                    value = uiState.name,
                    onValueChange = { viewModel.onNameChanged(it) },
                    placeholder = "Enter name",
                    placeholderStyle = TextStyle(color = Color.White),
                    textStyle = TextStyle(color = Color.White),
                    unfocusedBorderColor = Color.Black
                )

                // Email Input
                OutLinedInput(
                    label = stringResource(R.string.email),
                    value = uiState.email,
                    onValueChange = { viewModel.onEmailChanged(it) },
                    placeholder = stringResource(R.string.email),
                    placeholderStyle = TextStyle(color = Color.White),
                    textStyle = TextStyle(color = Color.White),
                    unfocusedBorderColor = Color.Black
                )

                // Password Input
                OutLinedInput(
                    label = stringResource(R.string.password),
                    value = uiState.password,
                    onValueChange = { viewModel.onPasswordChanged(it) },
                    placeholder = stringResource(R.string.password),
                    placeholderStyle = TextStyle(color = Color.White),
                    textStyle = TextStyle(color = Color.White),
                    trailingIcon = {
                        IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                            Icon(
                                painter = if (uiState.passwordVisible) painterResource(id = Icon.ic_eye) else painterResource(id = Icon.ic_eye_hide),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )

                // Sign Up Button
                Button(
                    onClick = { viewModel.signUp(onSignUpSuccess) },
                    enabled = uiState.canSubmit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(buttonBackground, shape = RoundedCornerShape(50)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Sign Up", color = Color.White)
                }

                // Bottom redirect
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Already have an account?", color = Color.White)
                    TextButton(onClick = onSignInClick) {
                        Text("Sign In", color = Color.White)
                    }
                }
            }
        }
    }
}
