package com.example.learnandroiddevelopmentbatch2.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnandroiddevelopmentbatch2.R
import com.example.learnandroiddevelopmentbatch2.activity.HeightScreen.Companion.storeValue
import com.example.learnandroiddevelopmentbatch2.activity.HeightScreen.Companion.unitStore
import com.example.learnandroiddevelopmentbatch2.databinding.ActivityWeightScreenBinding
import com.example.learnandroiddevelopmentbatch2.util.Constant
import com.example.learnandroiddevelopmentbatch2.util.changeUnitBg
import com.example.learnandroiddevelopmentbatch2.util.moveActNotFinish

class WeightScreen : AppCompatActivity() {
    private lateinit var binding: ActivityWeightScreenBinding
    private var baseWeightKg = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightScreenBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Share PreF GET OR iNITIALIZE
        val pref=getSharedPreferences("DbRef",MODE_PRIVATE)

        // Create editor
        val editor=pref.edit()

        val savedWeight = pref.getFloat(Constant.weightValueKey, 60f)
        val savedUnit = pref.getString(Constant.weightUnitKey, "kg")!!

        if (savedUnit == "kg") {
            baseWeightKg = savedWeight
        } else {
            baseWeightKg = lbsToKg(savedWeight)
        }


        //windsurf plugin auto code
        binding.apply {
            backIcon.setOnClickListener {
                // Activity Not Recreate Activity
                finish()

                //intent app activity recreate
            }

            if (savedUnit == "kg") {
                changeUnitBg(this@WeightScreen, kg, libs)
                unit.text = "kg"
                scale.setStartingPoint(savedWeight)
                hValue.text = "%.2f".format(savedWeight)

            } else {
                changeUnitBg(this@WeightScreen, libs, kg)
                unit.text = "lbs"
                scale.setStartingPoint(savedWeight)
                hValue.text = "%.2f".format(savedWeight)
            }

            storeValue = savedWeight
            unitStore = savedUnit


//            kg.setOnClickListener {
//                changeUnitBg(this@WeightScreen,kg,libs)
//                unit.text = "kg"
//            }
//            libs.setOnClickListener {
//                changeUnitBg(this@WeightScreen,libs,kg)
//                unit.text = "libs"
//
//            }



            kg.setOnClickListener {
                changeUnitBg(this@WeightScreen, kg, libs)
                unit.text = "kg"

                scale.setStartingPoint(baseWeightKg)
                hValue.text = "%.2f".format(baseWeightKg)

                storeValue = baseWeightKg
            }

            libs.setOnClickListener {
                changeUnitBg(this@WeightScreen, libs, kg)
                unit.text = "lbs"

                val lbsValue = kgToLbs(baseWeightKg)

                scale.setStartingPoint(lbsValue)
                hValue.text = "%.2f".format(lbsValue)

                storeValue = lbsValue
            }



            scale.setStartingPoint(60f)
            hValue.text="60.00"
//            scale.setUpdateListener { result ->
//                hValue.text = "%.2f".format(result)
//                storeValue=hValue.text.toString().toFloat()
//
//            }
            scale.setUpdateListener { result ->

                if (unit.text == "kg") {
                    baseWeightKg = result
                    hValue.text = "%.2f".format(result)
                    storeValue = result

                } else {
                    // result is in lbs UI → convert to base kg
                    baseWeightKg = lbsToKg(result)
                    val lbsValue = result
                    hValue.text = "%.2f".format(lbsValue)
                    storeValue = lbsValue
                }
            }




            nextBtn.setOnClickListener {
                if (unit.text == "kg") {
                    editor.putFloat(Constant.weightValueKey, baseWeightKg)
                } else {
                    editor.putFloat(Constant.weightValueKey, kgToLbs(baseWeightKg))
                }

                editor.putString(Constant.weightUnitKey, unit.text.toString())
                editor.apply()

                moveActNotFinish(this@WeightScreen, AgeScreen::class.java)



            }


        }
    }

    private fun kgToLbs(kg: Float): Float {
        return kg * 2.20462f
    }

    private fun lbsToKg(lbs: Float): Float {
        return lbs / 2.20462f
    }



}