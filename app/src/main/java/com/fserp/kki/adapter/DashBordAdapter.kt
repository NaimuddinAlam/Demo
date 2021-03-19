package com.fserp.kki.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.fserp.kki.R
import com.fserp.kki.activity.*
import com.fserp.kki.model.HomeModel

class DashBordAdapter(val context: Context, val homeModel: MutableList<HomeModel>) :
    RecyclerView.Adapter<DashBordAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBordAdapter.Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.card, null))
    }
    override fun onBindViewHolder(holder: DashBordAdapter.Holder, position: Int) {
        try {
            holder.tvname.text = homeModel[position].title
            holder.card.setOnClickListener {
                if(homeModel[position].title.equals("Created by me"))
                {
                   /* val intent=Intent(context,CreateBy::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK  or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)*/
                }else if(homeModel[position].title.equals("Assigned to me"))
                {
                    val intent= Intent(context,Assigned::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }else if(homeModel[position].title.equals("Complaint Status"))
                {
                    val intent= Intent(context,ComplaintStatus::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                }else if(homeModel[position].title.equals("Lodge a Complaint"))
                {
                    val intent= Intent(context,CreateBy::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }else if (homeModel[position].title.equals("Complaint Analysis"))
                {
                    val intent= Intent(context,ComplaintAnalysis::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
            }

        }catch (e:Exception){}

    }

    override fun getItemCount(): Int {
        return homeModel.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var tvname: TextView = view.findViewById(R.id.tvname)
        var card:CardView=view.findViewById(R.id.card)

    }
}