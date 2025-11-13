package com.example.learnandroiddevelopmentbatch2.callback

import android.widget.TextView
import com.example.learnandroiddevelopmentbatch2.model.MenuBlogModel

interface MainMenuListener {

    fun onMenuClick(model: MenuBlogModel)
}