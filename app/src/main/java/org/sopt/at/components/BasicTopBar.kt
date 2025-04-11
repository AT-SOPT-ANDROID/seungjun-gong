package org.sopt.at.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.R
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.basicColors


@Composable
fun BasicTopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ){
        Icon (
            imageVector = ImageVector.vectorResource(R.drawable.ic_back_arrow),
            contentDescription = stringResource(R.string.btn_basic_topbar_back),
            tint = basicColors.gray400,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun BasicTopBarPreview() {
    ATSOPTANDROIDTheme {
        BasicTopBar()
    }
}