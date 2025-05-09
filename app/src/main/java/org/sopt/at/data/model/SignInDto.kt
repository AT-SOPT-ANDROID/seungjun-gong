package org.sopt.at.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    @SerialName("loginId")
    val loginId: String,
    @SerialName("password")
    val password: String,
)

@Serializable
data class SignInResponseDto(
    @SerialName("userId")
    val userId: Int,
)