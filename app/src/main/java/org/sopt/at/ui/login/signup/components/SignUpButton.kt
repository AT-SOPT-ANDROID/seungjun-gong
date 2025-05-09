package org.sopt.at.ui.login.signup.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun SignUpButton(
    height: Dp = 50.dp,
    rounded: Dp = 4.dp,
    fontSize: TextUnit = 14.sp,
    onClick: () -> Unit = {},
    enabled: Boolean = false,
) {

    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        border = BorderStroke(
            width = 1.dp,
            color =
                if (enabled) {
                    Color.Transparent
                } else {
                    TvingTheme.colors.gray600
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
            color = if (enabled) {
                Color.Black
            } else {
                TvingTheme.colors.gray500
            },
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold,
        )
    }
}