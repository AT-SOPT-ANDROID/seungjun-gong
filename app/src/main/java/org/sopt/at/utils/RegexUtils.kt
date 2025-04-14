package org.sopt.at.utils

object RegexUtils {

    private val idRegex = Regex("^[a-z0-9]{6,12}$")
    private val passwordRegex = Regex("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*])[a-zA-Z0-9~!@#$%^&*]{8,15}$")

    fun isValidId(id: String): Boolean = idRegex.matches(id)
    fun isValidPassword(password: String): Boolean = passwordRegex.matches(password)

}