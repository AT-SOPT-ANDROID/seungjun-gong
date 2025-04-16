package org.sopt.at.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.presentation.components.BasicTopBar
import org.sopt.at.presentation.components.PasswordTextField
import org.sopt.at.presentation.components.SignUpErrorDialog
import org.sopt.at.presentation.components.TvingTextField
import org.sopt.at.utils.SharedPreferencesManager
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.basicColors
import org.sopt.at.utils.RegexUtils
import org.sopt.at.utils.RegexUtils.isValidPassword

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                SignUpScreen()
            }
        }
    }
}

@Composable
fun SignUpScreen() {

    var step by remember { mutableIntStateOf(1) }
    var id by remember { mutableStateOf("") }

    val context = LocalContext.current

    when (step) {
        1 -> {
            SignUpID {
                id = it
                step = 2
            }
        }

        2 -> {
            SignUpPassword { password ->
                SharedPreferencesManager.registerUser(id, password)
                val intent = Intent(context, SignInActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    putExtra("signup_success", true)
                }
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun SignUpID(
    nextStep: (String) -> Unit,
) {
    var inputId by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
    ) {
        BasicTopBar(modifier = Modifier.padding(vertical = 20.dp))

        Text(
            text = stringResource(R.string.sign_up_id_title),
            fontSize = 20.sp,
            color = basicColors.gray400,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.padding(15.dp))
        TvingTextField(
            value = inputId,
            onValueChange = { inputId = it },
            hint = stringResource(R.string.tf_id),
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = stringResource(R.string.sign_up_id_rule),
            fontSize = 12.sp,
            color = basicColors.gray600,
        )
        Spacer(modifier = Modifier.weight(1f))
        SignUpButton(
            onClick = {
                if (!RegexUtils.isValidId(inputId)) {
                    showDialog = true
                } else {
                    nextStep(inputId)
                }
            },
            isFilled = inputId.isNotBlank(),
        )
        Spacer(modifier = Modifier.height(50.dp))

        if (showDialog) {
            SignUpErrorDialog(
                onDismissRequest = { showDialog = false },
                text = stringResource(R.string.error_invalid_id),
            )
        }
    }
}

@Composable
fun SignUpPassword(nextStep: (String) -> Unit) {
    var inputPassword by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
    ) {
        BasicTopBar(modifier = Modifier.padding(vertical = 20.dp))

        Text(
            text = stringResource(R.string.sign_up_password_title),
            fontSize = 20.sp,
            color = basicColors.gray400,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.padding(15.dp))
        PasswordTextField(
            value = inputPassword,
            onValueChange = { inputPassword = it },
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = stringResource(R.string.sign_up_password_rule),
            fontSize = 12.sp,
            color = basicColors.gray600,
        )
        Spacer(modifier = Modifier.weight(1f))
        SignUpButton(
            onClick = {
                if (!isValidPassword(inputPassword)) {
                    showDialog = true
                } else {
                    nextStep(inputPassword)
                }
            },
            isFilled = inputPassword.isNotBlank(),
        )
        Spacer(modifier = Modifier.height(50.dp))

        if (showDialog) {
            SignUpErrorDialog(
                onDismissRequest = { showDialog = false },
                text = stringResource(R.string.error_invalid_password),
            )
        }
    }
}

@Composable
private fun SignUpButton(
    height: Dp = 50.dp,
    rounded: Dp = 4.dp,
    fontSize: TextUnit = 14.sp,
    onClick: () -> Unit = {},
    isFilled: Boolean,
) {

    OutlinedButton(
        onClick = onClick,
        enabled = isFilled,
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        border = BorderStroke(
            width = 1.dp,
            color =
                if (isFilled) {
                    Color.Transparent
                } else {
                    basicColors.gray600
                }
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            disabledContainerColor = Color.Black,
        ),
        shape = RoundedCornerShape(rounded)
    ) {
        Text(
            text = stringResource(R.string.btn_sign_up_next),
            color = if (isFilled) {
                Color.Black
            } else {
                basicColors.gray500
            },
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    ATSOPTANDROIDTheme {
        SignUpScreen()
    }
}