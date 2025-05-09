package org.sopt.at.utils

object RegexUtils {

    private val idRegex = Regex("^[a-zA-Z0-9]{8,20}$")
    private val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$")
    private val nicknameRegex = Regex("^[가-힣a-zA-Z0-9]{1,20}$")

    fun isValidId(id: String): Boolean = idRegex.matches(id)
    fun isValidPassword(password: String): Boolean = passwordRegex.matches(password)
    fun isValidNickname(nickname: String): Boolean = nicknameRegex.matches(nickname)

}
