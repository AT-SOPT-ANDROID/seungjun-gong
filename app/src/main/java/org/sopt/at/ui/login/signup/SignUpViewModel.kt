package org.sopt.at.ui.login.signup

import android.util.Log
import androidx.compose.ui.input.key.Key.Companion.J
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import org.sopt.at.data.model.BaseResponseDto
import org.sopt.at.data.model.SignUpRequestDto
import org.sopt.at.data.model.SignUpResponseDto
import org.sopt.at.data.service.ServicePool
import org.sopt.at.utils.RegexUtils.isValidId
import org.sopt.at.utils.RegexUtils.isValidNickname
import org.sopt.at.utils.RegexUtils.isValidPassword
import retrofit2.Callback

enum class SignUpStep {
    ID, NICKNAME, PASSWORD, SUCCESS
}

class SignUpViewModel : ViewModel() {

    private val _step = MutableStateFlow(SignUpStep.ID)
    val step: StateFlow<SignUpStep> = _step.asStateFlow()

    private val _loginId = MutableStateFlow("")
    val loginId: StateFlow<String> = _loginId.asStateFlow()

    private val _nickname = MutableStateFlow("")
    val nickname: StateFlow<String> = _nickname.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _isRuleError = MutableStateFlow(false)
    val isRuleError: StateFlow<Boolean> = _isRuleError.asStateFlow()

    private val _isDialogShow = MutableStateFlow(false)
    val isDialogShow: StateFlow<Boolean> = _isDialogShow.asStateFlow()

    fun showDialog(){
        _isDialogShow.value = true
    }

    fun dismissDialog() {
        _isDialogShow.value = false
        resetForm()
    }

    private val authService by lazy { ServicePool.authService }

    fun updateLoginId(newId: String) {
        _loginId.value = newId
    }

    fun updateNickname(newNickname: String) {
        _nickname.value = newNickname
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    private fun setIsRuleError(isError: Boolean) {
        _isRuleError.value = isError
    }

    private fun resetForm() {
        _loginId.value = ""
        _nickname.value = ""
        _password.value = ""
        _step.value = SignUpStep.ID
        _isRuleError.value = false
        _errorMessage.value = ""
    }


    private fun requestSignUp() {
        val request = SignUpRequestDto(
            loginId = _loginId.value,
            nickname = _nickname.value,
            password = _password.value
        )

        authService.signup(
            request = request
        ).enqueue(object :
            Callback<BaseResponseDto<SignUpResponseDto>> {
            override fun onResponse(
                call: retrofit2.Call<BaseResponseDto<SignUpResponseDto>>,
                response: retrofit2.Response<BaseResponseDto<SignUpResponseDto>>,
            ) {
                val code = response.code()
                if (response.isSuccessful) {
                    val body: BaseResponseDto<SignUpResponseDto>? = response.body()
                    _step.value = SignUpStep.SUCCESS
                } else {
                    val errorMessage = try {
                        val errorBody = response.errorBody()?.string()
                        Json.decodeFromString<BaseResponseDto<Unit>>(errorBody ?: "").message
                    } catch (e: Exception) {
                        "알 수 없는 오류가 발생했습니다."
                    }
                    setErrorMessage(errorMessage)
                    showDialog()
                }
            }

            override fun onFailure(
                call: retrofit2.Call<BaseResponseDto<SignUpResponseDto>>,
                t: Throwable,
            ) {
                Log.d("SignUpViewModel", "error: $t.message")
            }
        })
    }

    fun nextStep(
        onSuccess: () -> Unit = {},
    ) {
        when (_step.value) {
            SignUpStep.ID -> {
                if (!isValidId(_loginId.value)) {
                    setIsRuleError(true)
                    return
                }
                setIsRuleError(false)
                _step.value = SignUpStep.NICKNAME
            }

            SignUpStep.NICKNAME -> {
                if (!isValidNickname(_nickname.value)) {
                    setIsRuleError(true)
                    return
                }
                setIsRuleError(false)
                _step.value = SignUpStep.PASSWORD
            }

            SignUpStep.PASSWORD -> {
                if (!isValidPassword(_password.value)) {
                    setIsRuleError(true)
                    return
                }
                setIsRuleError(false)
                requestSignUp()
            }
            SignUpStep.SUCCESS -> Unit
        }
    }
}