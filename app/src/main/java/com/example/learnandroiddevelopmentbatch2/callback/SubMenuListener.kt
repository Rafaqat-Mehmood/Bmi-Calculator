package com.example.learnandroiddevelopmentbatch2.callback

import android.widget.TextView
import com.example.learnandroiddevelopmentbatch2.model.MenuBlogModel
import com.example.learnandroiddevelopmentbatch2.model.SubMenuBlogModel

interface SubMenuListener {

    fun onMenuClick(model: SubMenuBlogModel)
}