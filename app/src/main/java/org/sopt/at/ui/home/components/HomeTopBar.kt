 package org.sopt.at.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.common.StableImage
import org.sopt.at.ui.theme.TvingTheme
import org.sopt.at.utils.clickableWithoutRipple

@Composable
fun HomeTopBar(
    navigateToMyScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            imageVector = ImageVector.vectorResource(
                id = R.drawable.ic_tving_logo
            ),
            modifier = Modifier
                .height(20.dp),
            contentDescription = stringResource(R.string.tving_logo),
        )
        Spacer(modifier = Modifier.weight(1f))

        StableImage(
            drawableResId = R.drawable.ic_profile_default,
            contentDescription = stringResource(R.string.my_profile_button),
            modifier = Modifier
                .size(20.dp)
                .clip(RoundedCornerShape(2.dp))
                .clickableWithoutRipple(onClick = navigateToMyScreen),
            contentScale = ContentScale.Crop,
        )
    }
}
