package org.sopt.at.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.basicColors

@Composable
fun TvingTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                color = basicColors.gray600
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = basicColors.gray900,
            focusedContainerColor = basicColors.gray900,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = basicColors.gray600,
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
        ),
        shape = RoundedCornerShape(4.dp),
        singleLine = true,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ATSOPTANDROIDTheme {
        TvingTextField(
            value = "아이디",
            onValueChange = {},
            hint = "hinthint",
        )
    }
}