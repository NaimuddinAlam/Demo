package com.fserp.kki.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.fserp.kki.R
import com.fserp.kki.activity.CreateBy1
import com.fserp.kki.model.pojo.AllData

class PartAdapter(val constants: Context, val list: List<AllData>? = null, val  createBy1: CreateBy1): RecyclerView.Adapter<PartAdapter.Myholder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartAdapter.Myholder {
        return(Myholder(LayoutInflater.from(constants).inflate(R.layout.remove, null)))
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PartAdapter.Myholder, position: Int) {
        try {

            holder.text.text= list!![position].NATURE_NICK

            holder.checkbox.setOnClickListener {
                createBy1.addNature(list[position].NATURE_CODE.toString(),"DT")
                createBy1.addNature1("","GE")


            }





        }catch (e: Exception){}

    }


    override fun getItemCount(): Int {
        return  list!!.size
    }
    class Myholder(var view: View): RecyclerView.ViewHolder(view) {
        var text : TextView =view.findViewById(R.id.text)
        var checkbox: ImageView =view.findViewById(R.id.checkbox)

    }

}