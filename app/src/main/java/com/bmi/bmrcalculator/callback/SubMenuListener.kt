package com.bmi.bmrcalculator.callback

import com.bmi.bmrcalculator.model.SubMenuBlogModel

interface SubMenuListener {

    fun onMenuClick(model: SubMenuBlogModel)
}