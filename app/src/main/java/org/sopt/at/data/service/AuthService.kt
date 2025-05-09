package org.sopt.at.data.service

import org.sopt.at.data.model.SignUpRequestDto
import org.sopt.at.data.model.SignUpResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signup")
    suspend fun postSignUp(
        @Body request: SignUpRequestDto
    ): Call<SignUpResponseDto>
}