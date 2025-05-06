package org.sopt.at.ui.home

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.ui.home.model.TabBarCategory

class HomeViewModel : ViewModel() {

    private val _selectedCategory = MutableStateFlow(TabBarCategory.HOME)
    val selectedCategory: StateFlow<TabBarCategory> = _selectedCategory.asStateFlow()

    private val _bannerItems = MutableStateFlow<ImmutableList<Int>>(persistentListOf())
    val bannerItems: StateFlow<ImmutableList<Int>> = _bannerItems.asStateFlow()

    private val _todayItems = MutableStateFlow<ImmutableList<Int>>(persistentListOf())
    val todayItems: StateFlow<ImmutableList<Int>> = _todayItems.asStateFlow()

    private val _nowContentsItems = MutableStateFlow<ImmutableList<Int>>(persistentListOf())
    val nowContentsItems: StateFlow<ImmutableList<Int>> = _nowContentsItems.asStateFlow()

    private val dummyItems = listOf(
        R.drawable.img_home_poster1,
        R.drawable.img_home_poster2,
        R.drawable.img_home_poster3,
        R.drawable.img_home_poster4,
        R.drawable.img_home_poster5,
        R.drawable.img_home_poster6,
        R.drawable.img_home_poster7,
        R.drawable.img_home_poster8,
        R.drawable.img_home_poster9,
        R.drawable.img_home_poster10,
        R.drawable.img_home_poster11,
    )

    private fun getBannerItems(category: TabBarCategory): List<Int> {
        return when (category) {
            TabBarCategory.HOME -> dummyItems
            TabBarCategory.DRAMA -> listOf(R.drawable.img_home_drama_poster1)
            TabBarCategory.VARIETY -> listOf(R.drawable.img_home_variety_poster1)
            TabBarCategory.MOVIE -> listOf(R.drawable.img_home_movie_poster1)
            TabBarCategory.SPORTS -> listOf(R.drawable.img_home_sports_poster1)
            TabBarCategory.ANIME -> listOf(R.drawable.img_home_anime_poster1)
            TabBarCategory.NEWS -> listOf(R.drawable.img_home_news_poster1)
        }
    }

    fun loadSelectedBannerItems(category: TabBarCategory) {
        viewModelScope.launch {
            _selectedCategory.value = category
            _bannerItems.value = getBannerItems(category).toImmutableList()
        }
    }

    fun loadTodayItems() {
        viewModelScope.launch {
            _todayItems.value = dummyItems.toImmutableList()
        }
    }

    fun loadNowContentsItems() {
        viewModelScope.launch {
            _nowContentsItems.value = dummyItems.toImmutableList()
        }
    }

}