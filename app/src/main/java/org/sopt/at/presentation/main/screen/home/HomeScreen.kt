package org.sopt.at.presentation.main.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.presentation.main.screen.home.components.CustomBannerViewPager
import org.sopt.at.presentation.main.screen.home.components.CustomThumbnails
import org.sopt.at.presentation.main.screen.home.components.HomeTopBar
import org.sopt.at.presentation.main.screen.home.data.HomeViewModel
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    defaultHorizontalPadding: Dp = 20.dp,
) {
    val bannerItems by viewModel.bannerItems.collectAsState()
    val todayItems by viewModel.todayItems.collectAsState()
    val nowContentsItems by viewModel.nowContentsItems.collectAsState()

    // data load
    LaunchedEffect(Unit) {
        viewModel.loadBannerItems()
        viewModel.loadTodayItems()
        viewModel.loadNowContentsItems()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.Black),
    ) {
        item {
            Column {
                Spacer(Modifier.height(20.dp))
                HomeTopBar(
                    navigateToMyScreen = {},
                    modifier = Modifier
                        .padding(horizontal = defaultHorizontalPadding),
                )
                Spacer(Modifier.height(15.dp))
            }
        }

        stickyHeader {
            TapBarCategories(
                modifier = Modifier
                    .padding(bottom = 15.dp)
            )
        }

        item {
            CustomBannerViewPager(
                items = bannerItems,
                pagerState = rememberPagerState(pageCount = { bannerItems.size })
            )
        }

        item {
            CustomThumbnails(
                thumbnailItems = todayItems,
                headerText = "오늘의 티빙 TOP 20",
                showRanking = true,
                thumbnailDescription = "Today Top",
                defaultHorizontalPadding = defaultHorizontalPadding,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            CustomThumbnails(
                thumbnailItems = nowContentsItems,
                headerText = "지금 방영 중인 콘텐츠",
                thumbnailDescription = "Now Content",
                defaultHorizontalPadding = defaultHorizontalPadding,
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

    }
}

@Composable
fun TapBarCategories(
    modifier: Modifier = Modifier,
) {
    val categories = listOf(
        "드라마", "예능", "영화", "스포츠", "애니", "뉴스"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        categories.forEach { category ->
            Text(
                text = category,
                color = Color.White,
                fontSize = 14.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ATSOPTANDROIDTheme {
        HomeScreen(paddingValues = PaddingValues())
    }

}