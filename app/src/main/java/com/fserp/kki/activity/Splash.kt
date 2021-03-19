package com.fserp.kki.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fserp.kki.R
import com.fserp.kki.activity.auth.Signup
import com.fserp.kki.databinding.ActivitySplashBinding
import com.fserp.kki.utlis.SesssionManager

class Splash : AppCompatActivity() {
    var activitySplashBinding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showui()
    }
    fun showui() {
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        next()
    }
    fun next() {
        val handler = android.os.Handler()
        handler.postDelayed(Runnable {
            finish()
           SesssionManager(this).checkLogin()
        }, 3000)

    }


}