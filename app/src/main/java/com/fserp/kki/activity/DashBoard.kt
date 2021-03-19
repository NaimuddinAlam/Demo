package com.fserp.kki.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.fserp.kki.R
import com.fserp.kki.adapter.DashBordAdapter
import com.fserp.kki.databinding.ActivityDashBoardBinding

import com.fserp.kki.model.HomeModel
import com.google.android.material.navigation.NavigationView
import java.util.*

class DashBoard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var homeModels: MutableList<HomeModel>? = null
    var activityDashBoardBinding: ActivityDashBoardBinding? = null
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showui()
    }

    fun showui() {
        activityDashBoardBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_dash_board)
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "DashBord"
        activityDashBoardBinding!!.navigationheadercontainer!!.setNavigationItemSelectedListener(
            this
        )
        drawer = findViewById<DrawerLayout>(R.id.drawer)
        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        activityDashBoardBinding?.recyclerview?.layoutManager = GridLayoutManager(this, 2)
        datas()
    }

    fun datas() {
        homeModels = ArrayList()
        homeModels?.add((HomeModel("Lodge a Complaint")))
        homeModels?.add(HomeModel("Created by me"))
        homeModels?.add(HomeModel("Assigned to me"))
        homeModels?.add(HomeModel("Complaint Status"))
        homeModels?.add(HomeModel("Complaint Analysis"))
        val dashBordAdapter = DashBordAdapter(this, homeModels as ArrayList<HomeModel>)
        activityDashBoardBinding?.recyclerview?.adapter = dashBordAdapter
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_my_profile -> {
                val intent = Intent(this@DashBoard, Profile::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

            R.id.nav_created -> {
                val intent = Intent(this@DashBoard, CreateBy::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                startActivity(intent)
                drawer.closeDrawers()
                return true
            }

            R.id.nav_assigmed -> {
                val intent = Intent(this@DashBoard, Assigned::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                startActivity(intent)
                drawer.closeDrawers()
                return true

            }
            R.id.nav_lodge -> {
                val intent = Intent(this@DashBoard, Lodge::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                startActivity(intent)
                drawer.closeDrawers()
                return true

            }
            R.id.nav_sataus -> {
                val intent = Intent(this@DashBoard, ComplaintStatus::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                startActivity(intent)
                drawer.closeDrawers()
                return true

            }
            R.id.nav_anaysis -> {
                val intent = Intent(this@DashBoard, Assigned::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                startActivity(intent)
                drawer.closeDrawers()
                return true
            }
            R.id.nav_change -> {
                val intent = Intent(this@DashBoard, ChangePassword::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                startActivity(intent)
                drawer.closeDrawers()
                return true
            }

        }
        return true
    }


}