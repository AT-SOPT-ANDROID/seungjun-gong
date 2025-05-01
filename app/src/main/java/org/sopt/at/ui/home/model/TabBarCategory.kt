package org.sopt.at.ui.home.model

import androidx.annotation.StringRes
import org.sopt.at.R

enum class TabBarCategory(
    @StringRes val categoryRes: Int,
    @StringRes val liveSectionTitleRes: Int,
    @StringRes val recommendedSectionTitleRes: Int,
) {
    HOME(
        R.string.home_title,
        R.string.home_today_tving_top_20,
        R.string.home_now_contents
    ),
    DRAMA(
        R.string.home_tab_title_drama,
        R.string.home_section_title_live_drama,
        R.string.home_section_title_recommended_drama
    ),
    VARIETY(
        R.string.home_tab_title_variety,
        R.string.home_section_title_live_variety,
        R.string.home_section_title_recommended_variety
    ),
    MOVIE(
        R.string.home_tab_title_movie,
        R.string.home_section_title_live_movie,
        R.string.home_section_title_recommended_movie
    ),
    SPORTS(
        R.string.home_tab_title_sports,
        R.string.home_section_title_live_sports,
        R.string.home_section_title_recommended_sports
    ),
    ANIME(
        R.string.home_tab_title_anime,
        R.string.home_section_title_live_anime,
        R.string.home_section_title_recommended_anime
    ),
    NEWS(
        R.string.home_tab_title_news,
        R.string.home_section_title_live_news,
        R.string.home_section_title_recommended_news
    )
}