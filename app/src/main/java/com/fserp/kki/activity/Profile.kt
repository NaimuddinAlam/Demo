package com.fserp.kki.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.fserp.kki.R
import com.fserp.kki.databinding.ActivityProfileBinding
import java.io.File

class Profile : AppCompatActivity() {
    var activityProfile:ActivityProfileBinding?=null
    var toolbar:Toolbar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showui()
    }
    fun showui()
    {
        activityProfile=DataBindingUtil. setContentView(this, R.layout.activity_profile)
        toolbar=findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title="Profile"


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this@Profile, DashBoard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }
}