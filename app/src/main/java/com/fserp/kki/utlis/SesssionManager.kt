package com.fserp.kki.utlis

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.fserp.kki.activity.DashBoard
import com.fserp.kki.activity.auth.Signup
import com.fserp.kki.model.login.LoginData
import com.google.gson.Gson

@Suppress("DEPRECATION")
class SesssionManager {

    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var context: Context? = null

    constructor(context: Context) {
        this.context = context
        sharedPreferences = context.getSharedPreferences(Constants.SP, Constants.PRIVATE_MODE)
        editor = sharedPreferences!!.edit()
    }

    companion object {
        fun WriteSharePrefrence(context: Context, key: String, value: String) {
            val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
        }


        fun ReadSharePrefrence(context: Context, key: String): String {
            val data: String
            val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            data = sharedPreferences.getString(key, "").toString()
            editor.apply()
            return data
        }
    }
    fun setUser(data: LoginData?) {
        if (data == null) {
            editor?.putString("getUser", null)
        } else {
            val userjson = Gson().toJson(data)
            editor?.putString("getUser", userjson)
        }
        editor?.commit()
    }

    fun isLoggedIn(): String? {
        return sharedPreferences?.getString("getUser", null)
    }

    fun checkLogin() {
        if (isLoggedIn() == null) {
            val intent = Intent(context, Signup::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context?.startActivity(intent)
        } else {
            val intent = Intent(context, DashBoard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context?.startActivity(intent)
        }
    }

    fun logoutSession() {
        editor!!.clear()
        editor!!.commit()
        val intent = Intent(context, Signup::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context!!.startActivity(intent)


    }
}