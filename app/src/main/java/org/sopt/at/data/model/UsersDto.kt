package org.sopt.at.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserNickNameResponseDto(
    @SerialName("nickname")
    val nickname: String,
)