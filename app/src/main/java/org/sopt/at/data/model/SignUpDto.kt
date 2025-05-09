package org.sopt.at.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    @SerialName("loginId")
    val loginId: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String,
)

@Serializable
data class SignUpResponseDto(
    @SerialName("userId")
    val loginId: Int,
    @SerialName("nickname")
    val nickname: String,
)