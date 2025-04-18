package org.sopt.at.presentation.main.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme


@Composable
fun CustomBannerViewPager(
    pagerState: PagerState,
    items: List<Int>,
    modifier: Modifier = Modifier,
    contentPadding: Dp = 20.dp,
    pageSpacing: Dp = 10.dp,
    rounded: Dp = 10.dp,
    imageHeight: Dp = 480.dp,
) {
    HorizontalPager(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment
            .CenterVertically,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = contentPadding),
        pageSpacing = pageSpacing,
        snapPosition = SnapPosition.Start,
    ) { page ->

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .clip(RoundedCornerShape(rounded)),
            painter = painterResource(id = items[page]),
            contentScale = ContentScale.FillBounds,
            contentDescription = "배너 이미지 $page"
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun CustomBannerViewPagerPreview() {
    ATSOPTANDROIDTheme {
    }

}