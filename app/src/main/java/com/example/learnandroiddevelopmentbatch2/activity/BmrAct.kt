package com.example.learnandroiddevelopmentbatch2.activity

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnandroiddevelopmentbatch2.R
import com.example.learnandroiddevelopmentbatch2.activity.Dashboard.Companion.genderStr
import com.example.learnandroiddevelopmentbatch2.databinding.ActivityBmrBinding
import com.example.learnandroiddevelopmentbatch2.util.Constant
import com.example.learnandroiddevelopmentbatch2.util.changeUnitBg
import com.example.learnandroiddevelopmentbatch2.util.moveActNotFinish
import com.google.android.material.card.MaterialCardView

class BmrAct : AppCompatActivity() {
    private lateinit var binding: ActivityBmrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityBmrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Dashboard.bottomCardStr="BmrAct"

        val pref = getSharedPreferences("DbRef", MODE_PRIVATE)

        var gender=pref.getString(Constant.genderKey,"")
        var heightValue=pref.getFloat(Constant.heightValueKey,0.0f)
        var heightUnit=pref.getString(Constant.heightUnitKey,"")
        var weightValue=pref.getFloat(Constant.weightValueKey,0.0f)
        var weightUnit=pref.getString(Constant.weightUnitKey,"")
        var ageValue=pref.getInt(Constant.ageValueKey,0)

        // Create editor
        val editor = pref.edit()
        // Value set or Save in SharePreference
        editor.putBoolean(Constant.finishFirstTime, true)
        //Apply Changes or save
        editor.apply()
        
        
        binding.apply {

            back.setOnClickListener {
                finish()
            }
            // Values Set

            if (gender=="male")
            {
                genderCard(maleCard, femaleCard, male, female)

            }
            else
            {
                genderCard(femaleCard, maleCard, female, male)

            }


            weightInput.editText?.setText("$weightValue")
            heightInput.editText?.setText("$heightValue")
            ageInput.editText?.setText("$ageValue")


            if (heightUnit=="cm")
            {
                changeUnitBg(this@BmrAct,cm,ft)

            }
            else
            {
                changeUnitBg(this@BmrAct,ft,cm)

            }

            if (weightUnit=="kg")
            {
                changeUnitBg(this@BmrAct,kg,libs)

            }
            else
            {
                changeUnitBg(this@BmrAct,libs,kg)

            }

            //Weight Units
            kg.setOnClickListener {
                changeUnitBg(this@BmrAct,kg,libs)
            }
            libs.setOnClickListener {
                changeUnitBg(this@BmrAct,libs,kg)

            }

            //Height Units
            cm.setOnClickListener {
                changeUnitBg(this@BmrAct,cm,ft)
            }
            ft.setOnClickListener {
                changeUnitBg(this@BmrAct,ft,cm)

            }

            // Listeners Click
            maleCard.setOnClickListener {

                genderCard(maleCard, femaleCard, male, female)

                genderStr = "male"
            }

            femaleCard.setOnClickListener {

                genderCard(femaleCard, maleCard, female, male)
                genderStr = "female"

            }


        }
    }

    fun genderCard(
        activeCard: MaterialCardView, inactiveCard: MaterialCardView,
        activeText: TextView, inactiveText: TextView
    ) {
        activeCard.setCardBackgroundColor(getResources().getColor(R.color.common_color))
        inactiveCard.setCardBackgroundColor(getResources().getColor(R.color.card_color))
        activeText.setTextColor(getResources().getColor(R.color.white))
        inactiveText.setTextColor(getResources().getColor(R.color.common__light_color))
    }

    fun bottomCard(
        activeCard: MaterialCardView, inactiveCard: MaterialCardView,inactiveCard2: MaterialCardView,
    ) {
        activeCard.setCardBackgroundColor(getResources().getColor(R.color.common_color))
        inactiveCard.setCardBackgroundColor(getResources().getColor(R.color.bottom_nav_color))
        inactiveCard2.setCardBackgroundColor(getResources().getColor(R.color.bottom_nav_color))
    }
}