package com.bizmiz.bookieuz.utils

import android.content.Context
import android.content.SharedPreferences
import com.bizmiz.bookieuz.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_ID = "user_id"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String,userId: Int) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.putInt(USER_ID, userId)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
    fun fetchUserId(): Int {
        return prefs.getInt(USER_ID, 0)
    }
    fun removeToken() {
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.remove(USER_ID)
        editor.apply()
    }
}