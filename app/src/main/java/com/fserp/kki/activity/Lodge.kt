package com.fserp.kki.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.fserp.kki.R
import com.fserp.kki.activity.auth.Signup
import com.fserp.kki.databinding.ActivityLodgeBinding

class Lodge : AppCompatActivity() {
    var binding:ActivityLodgeBinding?=null
    var toolbar:Toolbar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showui()
    }
    fun showui()
    {
        binding=DataBindingUtil. setContentView(this,R.layout.activity_lodge)
        toolbar=findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title="Lodge a Complaint"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this@Lodge, DashBoard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}