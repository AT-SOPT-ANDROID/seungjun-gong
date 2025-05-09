package org.sopt.at.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private lateinit var prefs: SharedPreferences

    private const val PREF_NAME = "user_prefs"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_LOGIN_ID = "login_id"
    private const val KEY_LOGIN_PW = "login_pw"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun registerUser(id: String, password: String) {
        with(prefs.edit()) {
            putString(KEY_LOGIN_ID, id)
            putString(KEY_LOGIN_PW, password)
            apply()
        }
    }

    fun login(id: String, password: String): Boolean {
        val savedId = prefs.getString(KEY_LOGIN_ID, null)
        val savedPw = prefs.getString(KEY_LOGIN_PW, null)

        return if (id == savedId && password == savedPw) {
            with(prefs.edit()) {
                putBoolean(KEY_IS_LOGGED_IN, true)
                apply()
            }
            true
        } else {
            false
        }
    }

    fun saveUserId(id: Int) {
        with(prefs.edit()) {
            putInt(KEY_USER_ID, id)
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout() {
        with(prefs.edit()){
            putBoolean(KEY_IS_LOGGED_IN, false)
            apply()
        }
    }

    fun getUserId(): Int = prefs.getInt(KEY_USER_ID, -1)
}
