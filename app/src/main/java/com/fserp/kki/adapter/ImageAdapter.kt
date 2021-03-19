package com.fserp.kki.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fserp.kki.R
import com.fserp.kki.activity.CreateBy1

class ImageAdapter(
    val paths: ArrayList<String>? = null, var context: Context? = null,
    /* var filename: String? = null, var tempno: String? = null, var compressedImage1: File? = null*/
    var con: CreateBy1
) : RecyclerView.Adapter<ImageAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.Holder {
        return (Holder(LayoutInflater.from(context).inflate(R.layout.image, null)))
    }


    override fun onBindViewHolder(holder: ImageAdapter.Holder, position: Int) {

        //val myBitmap = BitmapFactory.decodeFile(f!!.absolutePath)
        var fil: String = paths!![position]
        holder.image.text = fil
        try {
            holder.remove.setOnClickListener {
              /*  paths.remove(paths!![position])
                notifyDataSetChanged()*/
                con.saveImage(paths!![position],"D")
                paths.remove(paths!![position])
                notifyDataSetChanged()
            }
        } catch (e: Exception) {
        }

    }


    override fun getItemCount(): Int {
        return paths!!.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var image: TextView = view.findViewById(R.id.image)
        var remove: ImageView = view.findViewById(R.id.remove)

    }
}