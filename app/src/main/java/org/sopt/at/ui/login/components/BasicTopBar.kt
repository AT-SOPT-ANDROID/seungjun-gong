package org.sopt.at.ui.login.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import org.sopt.at.R
import org.sopt.at.ui.theme.TvingTheme


@Composable
fun BasicTopBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_back_arrow),
            contentDescription = stringResource(R.string.btn_basic_topbar_back),
            tint = TvingTheme.colors.gray400,
        )
    }
}
