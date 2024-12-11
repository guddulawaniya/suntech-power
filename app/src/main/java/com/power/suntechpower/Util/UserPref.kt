package com.power.suntechpower.Util


import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.power.suntechpower.Models.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPref @Inject constructor(@ApplicationContext context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("userPref", Context.MODE_PRIVATE)
    private val gson: Gson = Gson()
    var users: User
        get() = gson.fromJson<Any>(
            preferences.getString("user", null),
            User::class.java
        ) as User
        set(user) {
            val gson = Gson()
            val loginRes = gson.toJson(user)
            preferences.edit().putString("user", loginRes).apply()
        }
    var isLogin: Boolean
        get() = preferences.getBoolean("isLoginA", false)
        set(login) = preferences.edit().putBoolean("isLoginA", login).apply()
    fun clearPref() {
        preferences.edit().clear().apply()
    }
    fun setToken(token: String?) {
        preferences.edit().putString("token", token).apply()
    }
    fun getToken(): String? {
        return preferences.getString("token", null)
    }
    fun setdarkmode(status: String?) {
        preferences.edit().putString("status", status).apply()
    }
    fun getdarkmode(): String? {
        return preferences.getString("status", null)
    }
    fun setProfileImage(profile_photo: String?) {
        preferences.edit().putString("profile_photo", profile_photo).apply()
    }
    fun getUserProfileImage(): String? {
        return preferences.getString("profile_photo", null)
    }
    fun setName(name: String) {
        preferences.edit().putString("name", name).apply()
    }

    fun getName(): String? {
        return preferences.getString("name", null)
    }
    fun setuserid(id: String) {
        preferences.edit().putString("id", id).apply()
    }
    fun getuserid(): String? {
        return preferences.getString("id", null)
    }

    fun setEmail(email: String?) {
        preferences.edit().putString("email", email).apply()
    }

    fun getEmail(): String? {
        return preferences.getString("email", null)
    }
    fun setTranstoken(email: String?) {
        preferences.edit().putString("transtoken", email).apply()
    }

    fun getTranstoken(): String? {
        return preferences.getString("transtoken", null)
    }
}