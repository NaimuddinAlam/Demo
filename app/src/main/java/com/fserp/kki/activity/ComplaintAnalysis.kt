package com.fserp.kki.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.fserp.kki.R
import com.fserp.kki.databinding.ActivityComplaintAnalysisBinding
import com.fserp.kki.databinding.ActivityComplaintStatusBinding

class ComplaintAnalysis : AppCompatActivity() {
    var binding: ActivityComplaintAnalysisBinding?=null
    var toolbar: Toolbar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showui()
    }
    fun showui()
    {
        binding= DataBindingUtil. setContentView(this,R.layout.activity_complaint_analysis)
        toolbar=findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title="Complaint Analysis"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this@ComplaintAnalysis, DashBoard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}