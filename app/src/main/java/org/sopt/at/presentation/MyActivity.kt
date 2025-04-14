package org.sopt.at.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.utils.SharedPreferencesManager
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.basicColors

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val userId = SharedPreferencesManager.getUserId() ?: ""
        val userPassword = SharedPreferencesManager.getUserPw() ?: ""

        setContent {
            ATSOPTANDROIDTheme {
                MyScreen(userId, userPassword)
            }
        }
    }
}

@Composable
fun MyScreen(
    id: String,
    password: String,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = "ID: $id",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        OutlinedButton(
            onClick = {
                SharedPreferencesManager.logout()

                val intent = Intent(context, SignInActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            border = BorderStroke(
                width = 1.dp,
                color = basicColors.gray600
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = stringResource(R.string.btn_logout),
                color = basicColors.gray500,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    ATSOPTANDROIDTheme {
        MyScreen("아이디", "비밀번호")
    }
}