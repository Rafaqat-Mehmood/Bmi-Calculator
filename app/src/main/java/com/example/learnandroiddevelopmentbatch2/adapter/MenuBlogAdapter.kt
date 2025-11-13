package com.example.learnandroiddevelopmentbatch2.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroiddevelopmentbatch2.R
import com.example.learnandroiddevelopmentbatch2.callback.MainMenuListener
import com.example.learnandroiddevelopmentbatch2.model.MenuBlogModel

class MenuBlogAdapter(var list: ArrayList<MenuBlogModel>,var listener: MainMenuListener): RecyclerView.Adapter<MenuBlogAdapter.MenuBlogHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuBlogHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_blog_item, parent, false)
        // Layout Define
        return MenuBlogHolder(view)
    }

    override fun onBindViewHolder(holder: MenuBlogHolder, position: Int) {
        // Holder Id access and data set karo ya lister or event set karo

        val model=list[position]
        holder.icon.setImageResource(model.image)
        holder.title.text=model.title

        holder.mainLayout.setOnClickListener {

            listener.onMenuClick(model)

        }

    }

    override fun getItemCount(): Int {
        return list.size
        // how much item display in a list or total item
    }


    // Holder
     class MenuBlogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Layout ID LIKE IMG,TEXT BUTTON HOLD

        var icon=itemView.findViewById<ImageView>(R.id.itemIcon)
        var title=itemView.findViewById<TextView>(R.id.itemText)
        var mainLayout=itemView.findViewById<ConstraintLayout>(R.id.mainLayout)
    }
}


