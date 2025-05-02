package org.sopt.at.ui.home.components

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import org.sopt.at.ui.common.StableImage


@Composable
fun CustomBannerViewPager(
    pagerState: PagerState,
    items: ImmutableList<Int>,
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

        StableImage(
            drawableResId = items[page],
            contentDescription = "배너 이미지 $page",
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .clip(RoundedCornerShape(rounded)),
            contentScale = ContentScale.FillBounds,
        )

    }
}
