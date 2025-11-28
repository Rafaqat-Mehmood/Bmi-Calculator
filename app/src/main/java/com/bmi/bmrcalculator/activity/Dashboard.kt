package com.bmi.bmrcalculator.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bmi.bmrcalculator.R
import com.bmi.bmrcalculator.databinding.ActivityDashboardBinding
import com.bmi.bmrcalculator.util.Constant
import com.bmi.bmrcalculator.util.changeUnitBg
import com.bmi.bmrcalculator.util.moveAct
import com.bmi.bmrcalculator.util.moveActNotFinish
import com.google.android.material.card.MaterialCardView

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    companion object {
        var genderStr = "male"

        var bottomCardStr = "Dashboard"
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i("TAG", "onCreate-Dashbaord: ")


        val pref = getSharedPreferences("DbRef", MODE_PRIVATE)

        var gender = pref.getString(Constant.genderKey, "")
        var heightValue = pref.getFloat(Constant.heightValueKey, 0.0f)
        var heightUnit = pref.getString(Constant.heightUnitKey, "")
        var weightValue = pref.getFloat(Constant.weightValueKey, 0.0f)
        var weightUnit = pref.getString(Constant.weightUnitKey, "")
        var ageValue = pref.getInt(Constant.ageValueKey, 0)

        // Create editor
        val editor = pref.edit()
        // Value set or Save in SharePreference
        editor.putBoolean(Constant.finishFirstTime, true)
        //Apply Changes or save
        editor.apply()

        Log.i(
            "TAG",
            "onCreate: $gender $heightValue $heightUnit $weightValue $weightUnit $ageValue"
        )

        var heightCm = heightValue  // always store internal height in cm
        var weightKg = weightValue  // always store internal weight in kg

        binding.apply {

            // Values Set

            if (gender == "male") {
                genderCard(maleCard, femaleCard, male, female)

            } else {
                genderCard(femaleCard, maleCard, female, male)

            }


            weightInput.editText?.setText("$weightValue")
            heightInput.editText?.setText("$heightValue")
            ageInput.editText?.setText("$ageValue")


            if (heightUnit == "cm") {
                changeUnitBg(this@Dashboard, cm, ft)
                cm.isSelected = true
                ft.isSelected = false
            } else {
                changeUnitBg(this@Dashboard, ft, cm)
                ft.isSelected = true
                cm.isSelected = false
            }

            if (weightUnit == "kg") {
                changeUnitBg(this@Dashboard, kg, libs)
                kg.isSelected = true
                libs.isSelected = false
            } else {
                changeUnitBg(this@Dashboard, libs, kg)
                libs.isSelected = true
                kg.isSelected = false
            }


            // Height Units
            cm.setOnClickListener {
                if (heightInput.editText?.text.toString().isNotEmpty() && ft.text != "cm") {
                    val currentFt = heightInput.editText?.text.toString().toFloat()
                    heightCm = feetToCm(currentFt)
                    heightInput.editText?.setText("%.2f".format(heightCm))
                    heightInput.hint = "Height (cm)"  // Set suffix
                    changeUnitBg(this@Dashboard, cm, ft)
                }
                cm.isSelected = true
                ft.isSelected = false
                selectUnit(cm, ft)

            }

            ft.setOnClickListener {
                if (heightInput.editText?.text.toString().isNotEmpty() && cm.text != "ft") {
                    val displayFt = cmToFeet(heightCm)
                    heightInput.editText?.setText("%.2f".format(displayFt))
                    heightInput.hint = "Height (ft)"  // Set suffix

                    changeUnitBg(this@Dashboard, ft, cm)
                }
                ft.isSelected = true
                cm.isSelected = false
                selectUnit(ft, cm)

            }

// Weight Units
            kg.setOnClickListener {
                if (weightInput.editText?.text.toString().isNotEmpty() && libs.text != "kg") {
                    val currentLbs = weightInput.editText?.text.toString().toFloat()
                    weightKg = lbsToKg(currentLbs)
                    weightInput.editText?.setText("%.2f".format(weightKg))
                    weightInput.hint = "Weight (kg)"  // Set suffix

                    changeUnitBg(this@Dashboard, kg, libs)
                }
                kg.isSelected = true
                libs.isSelected = false
                selectUnit(kg, libs)

            }

            libs.setOnClickListener {
                if (weightInput.editText?.text.toString().isNotEmpty() && kg.text != "lbs") {
                    val displayLbs = kgToLbs(weightKg)
                    weightInput.editText?.setText("%.2f".format(displayLbs))
                    weightInput.hint = "Weight (lbs)"  // Set suffix

                    changeUnitBg(this@Dashboard, libs, kg)
                }
                libs.isSelected = true
                kg.isSelected = false
                selectUnit(libs, kg)

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
                bottomCard(bmrCard, bmiCard, blogCard)
            }
//            bmiCard.setOnClickListener {
//                moveActNotFinish(this@Dashboard, Dashboard::class.java)
//                bottomCard(bmiCard, bmrCard,blogCard)
//
//            }
            blogCard.setOnClickListener {
                moveActNotFinish(this@Dashboard, BlogAct::class.java)
                bottomCard(blogCard, bmiCard, bmrCard)

            }


            nextBtn.setOnClickListener {

                val weightText = weightInput.editText?.text.toString()
                val heightText = heightInput.editText?.text.toString()

                if (weightText.isEmpty() || heightText.isEmpty()) {
                    Log.w("TAG", "Empty input")
                    return@setOnClickListener
                }

                    // Determine the current units from selected buttons
                    val currentHeightUnit = if (cm.isSelected) "cm" else "ft"
                    val currentWeightUnit = if (kg.isSelected) "kg" else "lbs"

                    Log.i("TAG", "nextBtn: "+currentHeightUnit+" "+currentWeightUnit)
                    val weightValue = weightText.toDouble()
                    val heightValue = heightText.toDouble()

                    // Calculate BMI using unified function
                    val bmi = calculateBMI(weightValue, heightValue, currentWeightUnit, currentHeightUnit)
                    val category = getBMICategory(bmi)

                    val intent = Intent(this@Dashboard, ResultAct::class.java).apply {
                        putExtra("name", "Body Mass Index")
                        putExtra("bmi", bmi)
                        putExtra("categories", category)
                    }
                    startActivity(intent)

                    Log.i("TAG", "Calculated BMI: $bmi, Category: $category")

            }

            //1-> Alert Dialog
            //2- Custom Dialog
            //3- Bottom Sheet Dialog

            heightInput.editText?.setOnClickListener {
                android.app.AlertDialog.Builder(this@Dashboard)
                    .setTitle("Edit Height")
                    .setMessage("Are you modify your height?.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        moveAct(this@Dashboard, HeightScreen::class.java)
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }

            weightInput.editText?.setOnClickListener {
                android.app.AlertDialog.Builder(this@Dashboard)
                    .setTitle("Edit Weight")
                    .setMessage("Are you modify your Weight?.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        moveAct(this@Dashboard, WeightScreen::class.java)
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
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
        activeCard: MaterialCardView,
        inactiveCard: MaterialCardView,
        inactiveCard2: MaterialCardView,
    ) {
        activeCard.setCardBackgroundColor(getResources().getColor(R.color.common_color))
        inactiveCard.setCardBackgroundColor(getResources().getColor(R.color.bottom_nav_color))
        inactiveCard2.setCardBackgroundColor(getResources().getColor(R.color.bottom_nav_color))
    }


    override fun onStart() {
        super.onStart()
        binding.apply {
            if (bottomCardStr != "Dashboard") {

                bottomCard(bmiCard, bmrCard, blogCard)
            }
        }
        Log.i("TAG", "onStart-Dashbaord: ")

    }



    // Height
    private fun cmToFeet(cm: Float): Float = cm / 30.48f
    private fun feetToCm(ft: Float): Float = ft * 30.48f

    // Weight
    private fun kgToLbs(kg: Float): Float = kg * 2.20462f
    private fun lbsToKg(lbs: Float): Float = lbs / 2.20462f




    fun calculateBMI(
        weightValue: Double,
        heightValue: Double,
        weightUnit: String,
        heightUnit: String
    ): Double {

        // --- Convert weight to kg ---
        val weightKg = when (weightUnit.lowercase().trim()) {
            "kg" -> weightValue
            "lbs" -> weightValue * 0.45359237
            else -> {
                Log.i("BMI", "Invalid weight unit: $weightUnit")
                return 0.0
            }
        }

        // --- Convert height to meters ---
        val heightMeters = when (heightUnit.lowercase().trim()) {
            "cm" -> heightValue / 100.0
            "ft" -> heightValue * 0.3048
            else -> {
                Log.e("BMI", "Invalid height unit: $heightUnit")
                return 0.0
            }
        }

        if (heightMeters <= 0 || weightKg <= 0) {
            Log.e("BMI", "Invalid numeric values: weight=$weightKg height=$heightMeters")
            return 0.0
        }

        return weightKg / (heightMeters * heightMeters)
    }





    fun getBMICategory(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal"
            bmi < 29.9 -> "Overweight"
            bmi < 39.9 -> "Obese"
            else -> "Morbidly Obese"
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    fun selectUnit(selected: TextView, unselected: TextView) {
        selected.isSelected = true
        unselected.isSelected = false

        Log.i("TAG", "selectUnit: "+selected.text+" "+unselected.text)
    }

}