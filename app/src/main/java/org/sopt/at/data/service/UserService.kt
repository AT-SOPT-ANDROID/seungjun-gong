package org.sopt.at.data.service

import org.sopt.at.data.model.BaseResponseDto
import org.sopt.at.data.model.UserNickNameResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/api/v1/users/me")
    fun getUserInfo(
        @Header("userId") userId: Int
    ): Call<BaseResponseDto<UserNickNameResponseDto>>
}