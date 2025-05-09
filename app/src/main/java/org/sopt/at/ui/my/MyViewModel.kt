package org.sopt.at.ui.my

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import org.sopt.at.data.model.BaseResponseDto
import org.sopt.at.data.model.SignUpRequestDto
import org.sopt.at.data.model.SignUpResponseDto
import org.sopt.at.data.model.UserNickNameResponseDto
import org.sopt.at.data.service.ServicePool
import org.sopt.at.data.service.ServicePool.authService
import org.sopt.at.ui.login.signup.SignUpResult
import org.sopt.at.utils.SharedPreferencesManager
import retrofit2.Callback

class MyViewModel : ViewModel() {

    private val userService by lazy { ServicePool.userService }

    private val _nickname = MutableStateFlow("")
    val nickname: StateFlow<String> = _nickname.asStateFlow()


    fun getUserNickName() {
        val userId = SharedPreferencesManager.getUserId()
        if (userId == -1) return

        userService.getUserInfo(userId).enqueue(object :
            Callback<BaseResponseDto<UserNickNameResponseDto>> {
            override fun onResponse(
                call: retrofit2.Call<BaseResponseDto<UserNickNameResponseDto>>,
                response: retrofit2.Response<BaseResponseDto<UserNickNameResponseDto>>,
            ) {
                if (response.isSuccessful) {
                    val body: BaseResponseDto<UserNickNameResponseDto>? = response.body()
                    _nickname.value = body?.data?.nickname.toString()
                } else {
                    val errorMessage = try {
                        val errorBody = response.errorBody()?.string()
                        Json.decodeFromString<BaseResponseDto<Unit>>(errorBody ?: "").message
                    } catch (e: Exception) {
                        "알 수 없는 오류가 발생했습니다."
                    }
                }
            }

            override fun onFailure(
                call: retrofit2.Call<BaseResponseDto<UserNickNameResponseDto>>,
                t: Throwable,
            ) {
                Log.d("MyViewModel", "error: $t.message")
            }
        })
    }

}
