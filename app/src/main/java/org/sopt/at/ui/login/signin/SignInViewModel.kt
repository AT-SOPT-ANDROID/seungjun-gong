package org.sopt.at.ui.login.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import org.sopt.at.data.model.BaseResponseDto
import org.sopt.at.data.model.SignInRequestDto
import org.sopt.at.data.model.SignInResponseDto
import org.sopt.at.data.service.ServicePool.authService
import org.sopt.at.utils.SharedPreferencesManager
import retrofit2.Callback

class SignInViewModel : ViewModel() {

    private val _loginId = MutableStateFlow("")
    val loginId: StateFlow<String> = _loginId.asStateFlow()


    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _signInResult = MutableStateFlow<SignInResult?>(null)
    val signInResult: StateFlow<SignInResult?> = _signInResult.asStateFlow()

    fun updateLoginId(id: String) {
        _loginId.value = id
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun requestSignIn() {
        val request = SignInRequestDto(
            loginId = _loginId.value,
            password = _password.value
        )

        authService.signin(
            request = request
        ).enqueue(object :
            Callback<BaseResponseDto<SignInResponseDto>> {
            override fun onResponse(
                call: retrofit2.Call<BaseResponseDto<SignInResponseDto>>,
                response: retrofit2.Response<BaseResponseDto<SignInResponseDto>>,
            ) {
                val code = response.code()
                if (response.isSuccessful) {
                    val body: BaseResponseDto<SignInResponseDto>? = response.body()
                    val userId = body?.data?.userId

                    SharedPreferencesManager.login(
                        id = _loginId.value,
                        password = _password.value
                    )
                    SharedPreferencesManager.saveUserId(
                        id = userId ?: -1
                    )

                    _signInResult.value = SignInResult.Success

                } else {
                    val errorMessage = try {
                        val errorBody = response.errorBody()?.string()
                        Json.decodeFromString<BaseResponseDto<Unit>>(errorBody ?: "").message
                    } catch (e: Exception) {
                        "알 수 없는 오류가 발생했습니다."
                    }
                    _signInResult.value = SignInResult.Failure(errorMessage)
                }
            }

            override fun onFailure(
                call: retrofit2.Call<BaseResponseDto<SignInResponseDto>>,
                t: Throwable,
            ) {
                Log.d("SignInViewModel", "error: $t.message")
            }
        })
    }
}

sealed class SignInResult {
    data object Success : SignInResult()
    data class Failure(val message: String) : SignInResult()
}
