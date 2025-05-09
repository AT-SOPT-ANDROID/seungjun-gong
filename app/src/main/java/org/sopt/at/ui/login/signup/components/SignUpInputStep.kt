package org.sopt.at.ui.login.signup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.login.components.BasicTopBar
import org.sopt.at.ui.login.components.PasswordTextField
import org.sopt.at.ui.login.components.TvingTextField
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun SignUpInputStep(
    title: String,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String,
    isError: Boolean,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isLoading: Boolean = false,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
    ) {
        BasicTopBar(modifier = Modifier.padding(vertical = 20.dp))

        Text(
            text = title,
            fontSize = 20.sp,
            color = TvingTheme.colors.gray400,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.padding(15.dp))

        if (isPassword) {
            PasswordTextField(
                value = value,
                onValueChange = onValueChange,
            )
        } else {
            TvingTextField(
                value = value,
                onValueChange = onValueChange,
                hint = hint,
            )
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = errorMessage,
            fontSize = 12.sp,
            color = if (isError) TvingTheme.colors.redA400 else TvingTheme.colors.gray600,
        )

        Spacer(modifier = Modifier.weight(1f))

        SignUpButton(
            onClick = onNextClick,
            enabled = !isError
        )

        Spacer(modifier = Modifier.height(50.dp))

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TvingTheme {
        SignUpInputStep(
            title = stringResource(R.string.sign_up_id_title),
            hint = stringResource(R.string.tf_id),
            value = "",
            onValueChange = {},
            errorMessage = "아이디가 중복되었습니다.",
            isError = true,
            onNextClick = {},
        )
    }

}