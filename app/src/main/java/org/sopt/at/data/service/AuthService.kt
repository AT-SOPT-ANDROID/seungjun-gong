package org.sopt.at.data.service

import org.sopt.at.data.model.BaseResponseDto
import org.sopt.at.data.model.SignInRequestDto
import org.sopt.at.data.model.SignInResponseDto
import org.sopt.at.data.model.SignUpRequestDto
import org.sopt.at.data.model.SignUpResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signup")
    fun signup(
        @Body request: SignUpRequestDto
    ): Call<BaseResponseDto<SignUpResponseDto>>

    @POST("/api/v1/auth/signin")
    fun signin(
        @Body request: SignInRequestDto
    ): Call<BaseResponseDto<SignInResponseDto>>
}