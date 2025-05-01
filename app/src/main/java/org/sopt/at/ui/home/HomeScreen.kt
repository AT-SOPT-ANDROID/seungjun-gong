package org.sopt.at.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.ui.home.components.CustomBannerViewPager
import org.sopt.at.ui.home.components.CustomThumbnails
import org.sopt.at.ui.home.components.HomeTopBar
import org.sopt.at.ui.home.components.TapBarCategories
import org.sopt.at.ui.home.model.TabBarCategory
import org.sopt.at.ui.theme.TvingTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigateToMyScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    defaultHorizontalPadding: Dp = 20.dp,
) {

    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val bannerItems by viewModel.bannerItems.collectAsState()
    val todayItems by viewModel.todayItems.collectAsState()
    val nowContentsItems by viewModel.nowContentsItems.collectAsState()

    // data load
    LaunchedEffect(Unit) {
        viewModel.loadSelectedBannerItems(TabBarCategory.HOME)
        viewModel.loadTodayItems()
        viewModel.loadNowContentsItems()
    }

    // 탭 활성화 상태, 백버튼 클릭시
    BackHandler(enabled = selectedCategory != TabBarCategory.HOME) {
        viewModel.loadSelectedBannerItems(TabBarCategory.HOME)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        item {
            Column {
                Spacer(Modifier.height(20.dp))
                HomeTopBar(
                    navigateToMyScreen = navigateToMyScreen,
                    modifier = Modifier
                        .padding(horizontal = defaultHorizontalPadding),
                )
                Spacer(Modifier.height(15.dp))
            }
        }

        stickyHeader {
            TapBarCategories(
                selectedCategory = selectedCategory,
                onTabSelected = { viewModel.loadSelectedBannerItems(it) }
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
                headerText = stringResource(selectedCategory.liveSectionTitleRes),
                showRanking = true,
                thumbnailDescription = stringResource(selectedCategory.liveSectionTitleRes),
                defaultHorizontalPadding = defaultHorizontalPadding,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            CustomThumbnails(
                thumbnailItems = nowContentsItems,
                headerText = stringResource(selectedCategory.recommendedSectionTitleRes),
                thumbnailDescription = stringResource(selectedCategory.recommendedSectionTitleRes),
                defaultHorizontalPadding = defaultHorizontalPadding,
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

    }
}
