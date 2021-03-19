package com.fserp.kki.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fserp.kki.R
import com.fserp.kki.adapter.AssignAdapter
import com.fserp.kki.databinding.ActivityAssignedBinding
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.utlis.Constants
import com.fserp.kki.utlis.SesssionManager
import com.fserp.kki.viewmodel.AssignComplaintViewModel

class Assigned : AppCompatActivity() {
    var binding: ActivityAssignedBinding? = null
    var toolbar: Toolbar? = null
    var recyclerview:RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showui()
    }
    @SuppressLint("WrongConstant")
    fun showui() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_assigned)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Assigned"
        recyclerview=findViewById(R.id.recyclerview) as RecyclerView
        recyclerview!!.layoutManager= LinearLayoutManager(this,LinearLayout.VERTICAL,false)

       assignComplain("","GE")
    }

    fun assignComplain(search: String, type: String) {

        AssignComplaintViewModel().assignComplain(
           "",
           "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        "",
            "",
            SesssionManager.ReadSharePrefrence(this, Constants.USER_ID),
            SesssionManager.ReadSharePrefrence(this, Constants.CENT_CODE),
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE),
            "",
           "",
            "",
            type,
            "","","","","","",search).observe(
            this,
            object :
                Observer<List<AllData>> {

                override fun onChanged(t: List<AllData>?) {
                    Log.d("alll","nsg"+t?.size)
                    recyclerview?.adapter=AssignAdapter(this@Assigned,t!!)


                }

            })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
           menuInflater.inflate(R.menu.menu,menu)
        val search = menu!!.findItem(R.id.action_search)
       val searchView= search.actionView as SearchView
        searchView.queryHint = Html.fromHtml("<font color = #ffffff>" + getResources().getString(R.string.Search) + "</font>");

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("serach","msf"+newText)
                assignComplain(newText.toString(),"SEARCH")
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this@Assigned, DashBoard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}