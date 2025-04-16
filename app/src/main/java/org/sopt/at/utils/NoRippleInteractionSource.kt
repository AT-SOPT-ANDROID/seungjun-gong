package org.sopt.at.utils

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

object NoRippleInteractionSource : MutableInteractionSource {
    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction): Boolean = true

    override val interactions: Flow<Interaction> = emptyFlow()
}