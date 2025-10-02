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
import com.example.learnandroiddevelopmentbatch2.databinding.ActivityDashboardBinding
import com.example.learnandroiddevelopmentbatch2.util.Constant
import com.example.learnandroiddevelopmentbatch2.util.changeUnitBg
import com.example.learnandroiddevelopmentbatch2.util.moveActNotFinish
import com.google.android.material.card.MaterialCardView

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private var genderStr = "male"

    companion object {
        var bottomCardStr = "Dashboard"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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

        Log.i("TAG", "onCreate: $gender $heightValue $heightUnit $weightValue $weightUnit $ageValue")

        binding.apply {

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
                changeUnitBg(this@Dashboard,cm,ft)

            }
            else
            {
                changeUnitBg(this@Dashboard,ft,cm)

            }

            if (weightUnit=="kg")
            {
                changeUnitBg(this@Dashboard,kg,libs)

            }
            else
            {
                changeUnitBg(this@Dashboard,libs,kg)

            }

            //Weight Units
            kg.setOnClickListener {
                changeUnitBg(this@Dashboard,kg,libs)
            }
            libs.setOnClickListener {
                changeUnitBg(this@Dashboard,libs,kg)

            }

            //Height Units
            cm.setOnClickListener {
                changeUnitBg(this@Dashboard,cm,ft)
            }
            ft.setOnClickListener {
                changeUnitBg(this@Dashboard,ft,cm)

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


            settingIcon.setOnClickListener {
                moveActNotFinish(this@Dashboard, SettingAct::class.java)
            }

            bmrCard.setOnClickListener {
                moveActNotFinish(this@Dashboard, BmrAct::class.java)
                bottomCard(bmrCard, bmiCard,blogCard)
            }
//            bmiCard.setOnClickListener {
//                moveActNotFinish(this@Dashboard, Dashboard::class.java)
//                bottomCard(bmiCard, bmrCard,blogCard)
//
//            }
            blogCard.setOnClickListener {
                moveActNotFinish(this@Dashboard, BlogAct::class.java)
                bottomCard(blogCard,bmiCard, bmrCard)

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


    override fun onStart() {
        super.onStart()
        binding.apply {
            if (bottomCardStr!="Dashboard"){

                bottomCard(bmiCard,bmrCard,blogCard)
            }
        }

    }

}