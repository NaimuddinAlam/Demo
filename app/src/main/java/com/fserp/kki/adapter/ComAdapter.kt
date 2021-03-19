package com.fserp.kki.adapter

import android.content.Context
import android.graphics.ColorSpace.Model
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.fserp.kki.R
import com.fserp.kki.activity.CreateBy1
import com.fserp.kki.model.pojo.AllData


class ComAdapter(val constants: Context, val list: List<AllData>? = null, val   createBy1: CreateBy1):RecyclerView.Adapter<ComAdapter.Myholder>() {


    var item_list: ArrayList<Model>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComAdapter.Myholder {
        return(Myholder(LayoutInflater.from(constants).inflate(R.layout.cardcomp, null)))
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ComAdapter.Myholder, position: Int) {
        try {

            holder.text.text= list!![position].NATURE_NAME

            holder.checkbox.setChecked(list[position].isSelected)
            if ( CreateBy1.lists.contains(list!![position].NATURE_NAME)) {
               // holder.checkbox.setChecked(true)

            } else {
                holder.checkbox.setChecked(false)

            }


            holder.checkbox.setOnClickListener {



                if ( holder.checkbox.isChecked == true) {


                    CreateBy1.lists.add(list[position].NATURE_NAME.toString())
                    createBy1.addNature(list[position].NATURE_CODE.toString(),"I")
                    //createBy1.addNature("","GE")

                } else {
                    CreateBy1.lists.remove(list[position].NATURE_NAME.toString())
                    createBy1.addNature(list[position].NATURE_CODE.toString(),"DT")
                    //createBy1.addNature("","GE")

                }



              //  Log.d("text", "msg" +)

               /* if (holder.checkbox.isChecked == true) {

                    val animals = ArrayList<String>()

                    animals.contains(list[position].NATURE_NAME!!)
                    Log.d("text", "msg" + animals)
                }*/
            }


        }catch (e: Exception){}

    }


    override fun getItemCount(): Int {
        return  list!!.size
    }
    class Myholder(var view: View):RecyclerView.ViewHolder(view) {
        var text :TextView =view.findViewById(R.id.text)
        var checkbox:CheckBox=view.findViewById(R.id.checkbox)

    }

}