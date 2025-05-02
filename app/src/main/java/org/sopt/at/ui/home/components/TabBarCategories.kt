package org.sopt.at.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.persistentListOf
import org.sopt.at.ui.home.model.TabBarCategory

val tabBarItems = persistentListOf(
    TabBarCategory.DRAMA,
    TabBarCategory.VARIETY,
    TabBarCategory.MOVIE,
    TabBarCategory.SPORTS,
    TabBarCategory.ANIME,
    TabBarCategory.NEWS,
)

@Composable
fun TapBarCategories(
    selectedCategory: TabBarCategory,
    onTabSelected: (TabBarCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        tabBarItems.forEach { category ->
            val isSelected by remember(selectedCategory) {
                derivedStateOf { selectedCategory == TabBarCategory.HOME || selectedCategory == category }
            }

            TabCategoryItem(
                category = category,
                isSelected = isSelected,
                onTabSelected = onTabSelected
            )
        }
    }
}

@Composable
private fun TabCategoryItem(
    category: TabBarCategory,
    isSelected: Boolean,
    onTabSelected: (TabBarCategory) -> Unit,
) {
    Text(
        text = stringResource(category.categoryRes),
        color = if (isSelected) Color.White else Color.Gray,
        modifier = Modifier
            .clickable { onTabSelected(category) }
            .padding(horizontal = 12.dp)
    )
}
