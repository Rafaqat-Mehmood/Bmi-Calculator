package com.bmi.bmrcalculator.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubMenuBlogModel(var id:Int,var time:String,var mainHeading:String,var image: Int): Parcelable