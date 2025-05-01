package org.sopt.at.ui.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.sopt.at.R
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun SignUpErrorDialog(
    onDismissRequest: () -> Unit,
    text: String,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = TvingTheme.colors.gray900,
            modifier = Modifier
                .width(300.dp)
                .wrapContentHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .padding(top = 5.dp),
                    text = text,
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = TvingTheme.colors.gray800,
                )
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .padding(bottom = 5.dp)
                        .fillMaxWidth()
                        .clickable(
                            onClick = onDismissRequest
                        ),
                    text = stringResource(R.string.sign_up_error_check),
                    fontSize = 12.sp,
                    color = TvingTheme.colors.gray500,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TvingTheme {
        SignUpErrorDialog(
            onDismissRequest = {},
            text = stringResource(R.string.error_invalid_id),
        )
    }
}