package com.bmi.bmrcalculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bmi.bmrcalculator.R
import com.bmi.bmrcalculator.callback.SubMenuListener
import com.bmi.bmrcalculator.model.SubMenuBlogModel
import com.google.android.material.card.MaterialCardView

class SubMenuBlogAdapter(var list: ArrayList<SubMenuBlogModel>, var listener: SubMenuListener): RecyclerView.Adapter<SubMenuBlogAdapter.SubMenuBlogHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMenuBlogHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.blog_detail_item, parent, false)
        // Layout Define
        return SubMenuBlogHolder(view)
    }

    override fun onBindViewHolder(holder: SubMenuBlogHolder, position: Int) {
        // Holder Id access and data set karo ya lister or event set karo

        val model=list[position]
        holder.icon.setImageResource(model.image)
        holder.title.text=model.mainHeading
        holder.time.text=model.time


        holder.mainLayout.setOnClickListener {

            listener.onMenuClick(model)

        }

    }

    override fun getItemCount(): Int {
        return list.size
        // how much item display in a list or total item
    }


    // Holder
     class SubMenuBlogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Layout ID LIKE IMG,TEXT BUTTON HOLD

        var icon=itemView.findViewById<ImageView>(R.id.dIcon)
        var title=itemView.findViewById<TextView>(R.id.mainTitle)
        var mainLayout=itemView.findViewById<MaterialCardView>(R.id.mainLayout)
        var time=itemView.findViewById<TextView>(R.id.time)
    }
}


