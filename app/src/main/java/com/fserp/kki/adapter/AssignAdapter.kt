package com.fserp.kki.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fserp.kki.R
import com.fserp.kki.activity.UpdateComplaintStatus
import com.fserp.kki.model.pojo.AllData

class AssignAdapter(val context: Context, val allData: List<AllData>? = null) :
    RecyclerView.Adapter<AssignAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignAdapter.Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.assigntome, null))
    }

    override fun onBindViewHolder(holder: AssignAdapter.Holder, position: Int) {
        try {


            holder.tvrepros.text = ":" + " " + " " + allData?.get(position)?.REPORTED_BY
            holder.tvcompids.text = ":" + " " + allData?.get(position)?.COMPLAINT_ID
            holder.tvdates.text = ":" + " " + allData?.get(position)?.COMPLAINT_DATE

            holder.tvrailwayno.text = ":" + " " + allData?.get(position)?.LEDGER_NAME
            holder.tvsheds.text = ":"+" "+ allData?.get(position)?.SUB_LEDGER_NAME

            holder.tvlocos.text = ":" + " " + allData?.get(position)?.LONO_NUMBER
            holder.tvstatusss.text = ":" + " " + allData?.get(position)?.STATUS_DESC
            holder.tvsummrys.text = ":" + " " + allData?.get(position)?.COMPLAINT_SHORT_DESC
            holder.tvassignto.text = ":" + " " + allData?.get(position)?.USERNAME




            holder.tvstatus.setOnClickListener {
                val intent = Intent(context, UpdateComplaintStatus::class.java)
                intent.putExtra("id", allData?.get(position)?.COMPLAINT_ID)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            holder.tvview.setOnClickListener {
                val intent = Intent(context, com.fserp.kki.activity.View::class.java)
                intent.putExtra("id", allData?.get(position)?.COMPLAINT_ID)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }

        } catch (e: Exception) {
        }
    }


    override fun getItemCount(): Int {
        return allData!!.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        var tvrepros: TextView = view.findViewById(R.id.tvrepros)
        var tvcompids: TextView = view.findViewById(R.id.tvcompids)
        var tvdates: TextView = view.findViewById(R.id.tvdates)
        var tvrailwayno: TextView = view.findViewById(R.id.tvrailwayno)
        var tvsheds: TextView = view.findViewById(R.id.tvsheds)
        var tvlocos: TextView = view.findViewById(R.id.tvlocos)
        var tvstatusss: TextView = view.findViewById(R.id.tvstatusss)
        var tvsummrys: TextView = view.findViewById(R.id.tvsummrys)
        val tvstatus: TextView = view.findViewById(R.id.tvstatus)
        var tvassignto: TextView = view.findViewById(R.id.tvassignto)
        var tvview: TextView = view.findViewById(R.id.tvview)


    }
}