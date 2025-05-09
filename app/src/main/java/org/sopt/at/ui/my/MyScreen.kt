package org.sopt.at.ui.my

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.R
import org.sopt.at.ui.MainActivity
import org.sopt.at.ui.common.StableImage
import org.sopt.at.ui.login.components.BasicTopBar
import org.sopt.at.ui.theme.TvingTheme
import org.sopt.at.utils.SharedPreferencesManager

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = viewModel(),
) {
    val context = LocalContext.current

    val nickname by viewModel.nickname.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUserNickName()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 20.dp),
    ) {
        BasicTopBar(modifier = Modifier.padding(vertical = 20.dp))
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            StableImage(
                drawableResId = R.drawable.ic_profile_default,
                contentDescription = stringResource(R.string.my_profile_image),
                modifier = Modifier
                    .size(74.dp)
                    .clip(RoundedCornerShape(2.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = nickname,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_my_edit),
                contentDescription = stringResource(R.string.my_edit_profile),
                modifier = Modifier
                    .size(24.dp)
            )
        }


        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        OutlinedButton(
            onClick = {
                SharedPreferencesManager.logout()
                val intent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            border = BorderStroke(
                width = 1.dp,
                color = TvingTheme.colors.gray600
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = stringResource(R.string.btn_logout),
                color = TvingTheme.colors.gray500,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Spacer(
            modifier = Modifier
                .height(100.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TvingTheme {
        MyScreen()
    }
}
