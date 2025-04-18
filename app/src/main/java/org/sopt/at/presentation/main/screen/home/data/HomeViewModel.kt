package org.sopt.at.presentation.main.screen.home.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.R

class HomeViewModel : ViewModel() {

    private val _bannerItems = MutableStateFlow<List<Int>>(emptyList())
    val bannerItems: StateFlow<List<Int>> = _bannerItems.asStateFlow()

    private val _todayItems = MutableStateFlow<List<Int>>(emptyList())
    val todayItems: StateFlow<List<Int>> = _todayItems.asStateFlow()

    private val _nowContentsItems = MutableStateFlow<List<Int>>(emptyList())
    val nowContentsItems: StateFlow<List<Int>> = _nowContentsItems.asStateFlow()

    private val items = listOf(
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

    fun loadBannerItems() {
        viewModelScope.launch {
            _bannerItems.value = items
        }
    }

    fun loadTodayItems() {
        viewModelScope.launch {
            _todayItems.value = items
        }
    }

    fun loadNowContentsItems() {
        viewModelScope.launch {
            _nowContentsItems.value = items
        }
    }

}