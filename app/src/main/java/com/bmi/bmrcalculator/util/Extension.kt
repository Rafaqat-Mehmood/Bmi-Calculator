package com.bmi.bmrcalculator.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.TextView
import com.bmi.bmrcalculator.R
import androidx.core.net.toUri


fun moveAct(currentAct: Activity, targetAct:Class<*>){
    var intent= Intent(currentAct, targetAct)
    currentAct.startActivity(intent)
    //Current Activty Finish
    currentAct.finish()
    // Complete App Finish
    //currentAct.finishAffinity()
}

fun moveActNotFinish(currentAct: Activity, targetAct:Class<*>) {
    var intent= Intent(currentAct, targetAct)
    currentAct.startActivity(intent)

}

 fun changeUnitBg(context: Activity, kg: TextView, libs:TextView) {
    kg.setBackgroundResource(R.drawable.ftcm_fill_bg)
    libs.setBackgroundResource(R.drawable.ftcm_whilte_fill_bg)
    kg.setTextColor(context.getColor(R.color.white))
    libs.setTextColor(context.getColor(R.color.common__light_color))
}

// Rate Us
// Share
// Email
// More App
// Package
// save image ya store package
fun Activity.rateUs() {
    val packageName = this.packageName
    try {
        val uri = "market://details?id=$packageName".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.android.vending")
        startActivity(intent)
    }
    catch (e: Exception) {
        // Fallback to browser
        val uri = "https://play.google.com/store/apps/details?id=$packageName".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}

fun Activity.shareApp() {
    val packageName = this.packageName
    val shareText = "This is my Bmi and Bmr App Calculator:\nhttps://play.google.com/store/apps/details?id=$packageName"

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareText)
    }

    startActivity(Intent.createChooser(intent, "Share App"))
}

fun Activity.moreApps() {
    val developerId = "FJK+Studio"   // Example: "TechConnectMinds"
    try {
        val uri = "market://dev?id=$developerId".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.android.vending")
        startActivity(intent)
    } catch (e: Exception) {
        // Fallback to browser
        val uri = "https://play.google.com/store/apps/developer?id=$developerId".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}




