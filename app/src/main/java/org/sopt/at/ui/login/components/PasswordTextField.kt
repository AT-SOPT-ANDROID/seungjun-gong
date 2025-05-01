package org.sopt.at.ui.login.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    var isShowPassword by remember { mutableStateOf(false) }

    val passwordShowState: (Boolean) -> Int = {
        if (it) {
            R.drawable.ic_password_on
        } else {
            R.drawable.ic_password_off
        }
    }

    TvingTextField(
        value = value,
        onValueChange = onValueChange,
        hint = stringResource(R.string.tf_password),
        trailingIcon = {
            IconButton(
                onClick = {
                    isShowPassword = !isShowPassword
                },
                modifier = Modifier.padding(end = 5.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = passwordShowState(isShowPassword)
                    ),
                    contentDescription = stringResource(R.string.btn_password_visibility),
                    tint = TvingTheme.colors.gray600,
                )
            }
        },
        visualTransformation = (if (isShowPassword) VisualTransformation.None
        else PasswordVisualTransformation()),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TvingTheme {
        PasswordTextField(
            value = "비밀번호",
            onValueChange = {}
        )
    }

}