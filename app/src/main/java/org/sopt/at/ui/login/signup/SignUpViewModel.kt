package org.sopt.at.ui.login.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class SignUpStep {
    ID, NICKNAME, PASSWORD, COMPLETE
}

class SignUpViewModel: ViewModel() {

    private val _step = MutableStateFlow(SignUpStep.ID)
    val step: StateFlow<SignUpStep> = _step.asStateFlow()

    private val _userId = MutableStateFlow("")
    val uerId: StateFlow<String> = _userId.asStateFlow()

    private val _nickname = MutableStateFlow("")
    val nickname: StateFlow<String> = _nickname.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun updateUserId(newId: String) {
        _userId.value = newId
    }

    fun updateNickname(newNickname: String) {
        _nickname.value = newNickname
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun nextStep() {
        _step.value = when (_step.value) {
            SignUpStep.ID -> SignUpStep.NICKNAME
            SignUpStep.NICKNAME -> SignUpStep.PASSWORD
            SignUpStep.PASSWORD -> SignUpStep.COMPLETE
            SignUpStep.COMPLETE -> SignUpStep.COMPLETE
        }
    }
}