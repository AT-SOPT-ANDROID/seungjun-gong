package org.sopt.at.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.presentation.components.BasicTopBar
import org.sopt.at.presentation.components.PasswordTextField
import org.sopt.at.presentation.components.TvingTextField
import org.sopt.at.presentation.main.MainActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.basicColors
import org.sopt.at.utils.SharedPreferencesManager

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            if (intent.getBooleanExtra("signup_success", false)) {
                Toast.makeText(
                    this,
                    stringResource(R.string.success_signin_message), Toast.LENGTH_SHORT
                ).show()
            }

            ATSOPTANDROIDTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.imePadding(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) {
                    SignInScreen(
                        modifier = Modifier
                            .padding(it)
                            .imePadding(),
                        snackbarHostState = snackbarHostState,
                    )
                }
            }
        }
    }
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    var inputId by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
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
            value = inputId,
            onValueChange = { inputId = it },
            hint = stringResource(R.string.tf_id)
        )
        Spacer(modifier = Modifier.height(15.dp))
        PasswordTextField(
            value = inputPassword,
            onValueChange = { inputPassword = it },
        )
        Spacer(modifier = Modifier.height(30.dp))
        SignInButton(
            inputId, inputPassword,
            onClick = {
                if (SharedPreferencesManager.login(id = inputId, password = inputPassword)) {
                    val intent = Intent(context, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(intent)
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = context.getString(R.string.signin_fail_message),
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
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
                    val intent = Intent(context, SignUpActivity::class.java)
                    context.startActivity(intent)
                })
        }
        Spacer(modifier = Modifier.height(25.dp))
        PolicyText()
    }
}

@Composable
private fun PolicyText() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = buildAnnotatedString {
            append("이 사이트는 Google reCAPTCHA로 보호되며,\n")

            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Google 개인정보 처리방침")
            }

            append("과 ")

            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("서비스 약관")
            }

            append("이 적용됩니다.")
        },
        fontSize = 10.sp,
        lineHeight = 14.sp,
        textAlign = TextAlign.Center,
        color = basicColors.gray700,
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
            containerColor = basicColors.redA400,
            disabledContainerColor = basicColors.gray800,
        ),
        shape = RoundedCornerShape(rounded)
    ) {
        Text(
            text = stringResource(R.string.btn_sign_in),
            color = if (isLoginFormFilled) {
                Color.White
            } else {
                basicColors.gray500
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
    color: Color = basicColors.gray500,
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
    color: Color = basicColors.gray800,
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

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    ATSOPTANDROIDTheme {
        SignInScreen()
    }
}