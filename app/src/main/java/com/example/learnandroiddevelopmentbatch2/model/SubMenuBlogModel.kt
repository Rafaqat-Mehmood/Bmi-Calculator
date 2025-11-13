package com.example.learnandroiddevelopmentbatch2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubMenuBlogModel(var id:Int,var time:String,var mainHeading:String,var image: Int): Parcelable