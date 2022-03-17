package com.example.posyanduapp.Helper

import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import com.example.posyanduapp.Model.User
import com.google.gson.Gson

class SharedPref(activity: ComponentActivity) {

    val login = "login"
    val mypref = "MAIN_PREF"
    val user = "user"
    val sp:SharedPreferences


    init {
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status:Boolean){
        sp.edit().putBoolean(login,status).apply()
    }

    fun getStatusLogin():Boolean{
        return sp.getBoolean(login,false)
    }

    fun setUser(value:User){
        val data : String = Gson().toJson(value, User::class.java)
        sp.edit().putString(user, data).apply()
    }

    fun getUser() :User?{
        val data : String = sp.getString(user,null) ?: return null
        return Gson().fromJson<User>(data, User::class.java)
    }
}