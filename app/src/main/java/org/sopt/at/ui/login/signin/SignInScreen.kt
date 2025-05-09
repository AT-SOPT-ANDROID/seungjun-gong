package org.sopt.at.ui.login.signin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.ui.login.components.BasicTopBar
import org.sopt.at.ui.login.components.PasswordTextField
import org.sopt.at.ui.login.components.TvingTextField
import org.sopt.at.ui.theme.TvingTheme
import org.sopt.at.utils.SharedPreferencesManager

@Composable
fun SignInScreen(
    navigateToHomeScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = viewModel(),
) {
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val loginId by viewModel.loginId.collectAsState()
    val password by viewModel.password.collectAsState()
    val signInResult by viewModel.signInResult.collectAsState()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(currentBackStackEntry) {
        val savedStateHandle = currentBackStackEntry?.savedStateHandle
        val signUpSuccess = savedStateHandle?.get<Boolean>("signUpSuccess") == true

        if (signUpSuccess) {
            Toast.makeText(
                context,
                context.getString(R.string.success_signup_message),
                Toast.LENGTH_SHORT
            ).show()
            savedStateHandle?.remove<Boolean>("signUpSuccess")
        }
    }

    LaunchedEffect(signInResult) {
        when (signInResult) {
            is SignInResult.Success -> {
                navigateToHomeScreen()
            }
            is SignInResult.Failure -> {
                val errorMessage = (signInResult as SignInResult.Failure).message
                    snackbarHostState.showSnackbar(
                        message = errorMessage,
                        duration = SnackbarDuration.Short
                    )
            }
            null -> {}
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .imePadding(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
        ) {
            BasicTopBar(modifier = Modifier.padding(vertical = 20.dp))
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.sign_in_title),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.height(30.dp))
            TvingTextField(
                value = loginId,
                onValueChange = viewModel::updateLoginId,
                hint = stringResource(R.string.tf_id)
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                value = password,
                onValueChange = viewModel::updatePassword,
            )
            Spacer(modifier = Modifier.height(30.dp))
            SignInButton(
                loginId, password,
                onClick = viewModel::requestSignIn
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AuthText(stringResource(R.string.btn_find_id))
                VerticalDividerWithSpacing()
                AuthText(stringResource(R.string.btn_find_password))
                VerticalDividerWithSpacing()
                AuthText(
                    stringResource(R.string.btn_sign_up),
                    onClick = {
                        navigateToSignUpScreen()
                    })
            }
            Spacer(modifier = Modifier.height(25.dp))
            PolicyText()
        }
    }
}

@Composable
private fun PolicyText() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = buildAnnotatedString {
            append(stringResource(R.string.sign_in_policy_description1))

            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append(stringResource(R.string.sign_in_policy_description2))
            }

            append(stringResource(R.string.sign_in_policy_description3))

            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append(stringResource(R.string.sign_in_policy_description4))
            }

            append(stringResource(R.string.sign_in_policy_description5))
        },
        fontSize = 10.sp,
        lineHeight = 14.sp,
        textAlign = TextAlign.Center,
        color = TvingTheme.colors.gray700,
    )
}

@Composable
private fun SignInButton(
    inputId: String,
    inputPassword: String,
    height: Dp = 50.dp,
    rounded: Dp = 4.dp,
    fontSize: TextUnit = 16.sp,
    onClick: (() -> Unit),
) {
    val isLoginFormFilled = inputId.isNotBlank() && inputPassword.isNotBlank()

    Button(
        onClick = onClick,
        enabled = isLoginFormFilled,
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        colors = ButtonDefaults.buttonColors(
            containerColor = TvingTheme.colors.redA400,
            disabledContainerColor = TvingTheme.colors.gray800,
        ),
        shape = RoundedCornerShape(rounded)
    ) {
        Text(
            text = stringResource(R.string.btn_sign_in),
            color = if (isLoginFormFilled) {
                Color.White
            } else {
                TvingTheme.colors.gray500
            },
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun AuthText(
    text: String,
    fontSize: TextUnit = 14.sp,
    color: Color = TvingTheme.colors.gray500,
    onClick: (() -> Unit)? = null,
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        modifier = Modifier
            .then(
                if (onClick != null) Modifier.clickable { onClick() }
                else Modifier
            )
    )
}

@Composable
fun VerticalDividerWithSpacing(
    color: Color = TvingTheme.colors.gray800,
    height: Dp = 14.dp,
    horizontalPadding: Dp = 10.dp,
) {
    VerticalDivider(
        modifier = Modifier
            .height(height)
            .padding(horizontal = horizontalPadding),
        color = color
    )
}
