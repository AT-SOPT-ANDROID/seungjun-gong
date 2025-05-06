package org.sopt.at.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import org.sopt.at.ui.common.StableImage

@Composable
fun CustomThumbnails(
    thumbnailItems: ImmutableList<Int>,
    headerText: String,
    modifier: Modifier = Modifier,
    showRanking: Boolean = false,
    thumbnailDescription: String = "",
    defaultHorizontalPadding: Dp = 20.dp,
) {
    Column {
        Text(
            modifier = modifier
                .padding(horizontal = defaultHorizontalPadding)
                .padding(
                    top = 20.dp,
                    bottom = 10.dp,
                ),
            text = headerText,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold
        )
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = defaultHorizontalPadding,
            ),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(thumbnailItems) { index, item ->
                ThumbnailCard(
                    thumbnailItem = item,
                    rank = index,
                    showRanking = showRanking,
                    thumbnailDescription = "thumbnailDescription ${index + 1}",
                )
            }
        }
    }
}

@Composable
fun ThumbnailCard(
    thumbnailItem: Int,
    rank: Int,
    showRanking: Boolean,
    thumbnailDescription: String = "",
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        if (showRanking) {
            Text(
                text = "${rank + 1}",
                color = Color.White,
                fontSize = 80.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                letterSpacing = (-4).sp,
                modifier = Modifier.height(76.dp)
            )
            Spacer(Modifier.width(5.dp))
        }

        StableImage(
            drawableResId = thumbnailItem,
            contentDescription = thumbnailDescription,
            modifier = Modifier
                .size(width = 100.dp, height = 140.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.FillBounds,
        )
    }
}
