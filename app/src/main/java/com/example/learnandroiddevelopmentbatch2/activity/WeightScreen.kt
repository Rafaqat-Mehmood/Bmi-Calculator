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

        //windsurf plugin auto code
        binding.apply {
            backIcon.setOnClickListener {
                // Activity Not Recreate Activity
                finish()

                //intent app activity recreate
            }

            kg.setOnClickListener {
                changeUnitBg(this@WeightScreen,kg,libs)
                unit.text = "kg"
            }
            libs.setOnClickListener {
                changeUnitBg(this@WeightScreen,libs,kg)
                unit.text = "libs"

            }

            scale.setStartingPoint(165f)
            hValue.text="165.00"
            scale.setUpdateListener { result ->
                hValue.text = "%.2f".format(result)
                storeValue=hValue.text.toString().toFloat()

            }

            nextBtn.setOnClickListener {
                unitStore=unit.text.toString()

                // Value set or Save in SharePreference
                editor.putFloat(Constant.weightValueKey,storeValue)
                editor.putString(Constant.weightUnitKey,unitStore)

                //Apply Changes or save
                editor.apply()
                moveActNotFinish(this@WeightScreen, AgeScreen::class.java)



            }


        }
    }


}