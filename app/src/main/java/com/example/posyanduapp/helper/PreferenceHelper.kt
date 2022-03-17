package com.example.posyanduapp.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

@Suppress("DEPRECATION")
object PreferenceHelper {
    val ID_USER = "ID_USER"
    val USER_NAME = "NAME"
    val USER_NAME_1 = "NAME_1"
    val USER_EMAIL = "EMAIL"
    val USER_PASSWORD = "PASSWORD"
    val USER_API_TOKEN = "API_TOKEN"
    val USER_ROLE = "ROLE"
    val USER_PHONE = "PHONE"
    val PHONE_1 = "PHONE_1"
    val PHONE_2 = "PHONE_2"
    val USER_ADDRESS = "ADDRESS"
    val IS_LOGIN = "IS_LOGIN"


    // method when want to use default Shared Preference
    fun defaultPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    // method when want to use custom Shared Preference
    fun customPreference(context: Context, filename: String): SharedPreferences =
        context.getSharedPreferences(filename, Context.MODE_PRIVATE)

    // Shared Preference Editor
    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    // idUser getter setter
    var SharedPreferences.idUser
        get() = getString(ID_USER, "")
        set(value) {
            editMe {
                it.putString(ID_USER, value)
            }
        }

    // name getter setter
    var SharedPreferences.name
        get() = getString(USER_NAME, "")
        set(value) {
            editMe {
                it.putString(USER_NAME, value)
            }
        }

    // name_1 getter setter
    var SharedPreferences.name_1
        get() = getString(USER_NAME_1, "")
        set(value) {
            editMe {
                it.putString(USER_NAME_1, value)
            }
        }

    // email getter setter
    var SharedPreferences.email
        get() = getString(USER_EMAIL, "")
        set(value) {
            editMe {
                it.putString(USER_EMAIL, value)
            }
        }

    // password getter setter
    var SharedPreferences.password
        get() = getString(USER_PASSWORD, "")
        set(value) {
            editMe {
                it.putString(USER_PASSWORD, value)
            }
        }

    // api_token getter setter
    var SharedPreferences.api_token
        get() = getString(USER_API_TOKEN, "")
        set(value) {
            editMe {
                it.putString(USER_API_TOKEN, value)
            }
        }

    // role getter setter
    var SharedPreferences.role
        get() = getString(USER_ROLE, "")
        set(value) {
            editMe {
                it.putString(USER_ROLE, value)
            }
        }

    // phone getter setter
    var SharedPreferences.phone
        get() = getString(USER_PHONE, "")
        set(value) {
            editMe {
                it.putString(USER_PHONE, value)
            }
        }

    // phone getter setter
    var SharedPreferences.phone1
        get() = getString(PHONE_1, "")
        set(value) {
            editMe {
                it.putString(PHONE_1, value)
            }
        }

    // phone getter setter
    var SharedPreferences.phone2
        get() = getString(PHONE_2, "")
        set(value) {
            editMe {
                it.putString(PHONE_2, value)
            }
        }

    // address getter setter
    var SharedPreferences.address
        get() = getString(USER_ADDRESS, "")
        set(value) {
            editMe {
                it.putString(USER_ADDRESS, value)
            }
        }

    var SharedPreferences.islogin
        get() = getBoolean(IS_LOGIN, false)
        set(value) {
            editMe {
                it.putBoolean(IS_LOGIN, value)
            }
        }

    // clear value
    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}