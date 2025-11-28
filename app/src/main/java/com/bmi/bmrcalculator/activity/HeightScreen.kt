package com.bmi.bmrcalculator.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bmi.bmrcalculator.R
import com.bmi.bmrcalculator.databinding.ActivityHeightScreenBinding
import com.bmi.bmrcalculator.util.Constant
import com.bmi.bmrcalculator.util.changeUnitBg
import com.bmi.bmrcalculator.util.moveActNotFinish

class HeightScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHeightScreenBinding

    companion object {
        var unitStore = "cm"
        var storeValue = 165.00f

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHeightScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Share PreF GET OR iNITIALIZE
        val pref = getSharedPreferences("DbRef", MODE_PRIVATE)

        // Create editor
        val editor = pref.edit()


        binding.apply {


            backIcon.setOnClickListener {
                // Activity Not Recreate Activity
                finish()

            }


            // Initial
            scale.setStartingPoint(storeValue)
            hValue.text = "%.2f".format(storeValue)


            // Internal storage always in cm
            var storeValueCm = 165f

            // Flag to ignore programmatic updates
            var isUpdatingScale = false

            cm.setOnClickListener {
                if (unit.text != "cm") {
                    unit.text = "cm"
                    isUpdatingScale = true
                    // Use internal cm value to set scale
                    scale.setStartingPoint(storeValueCm)
                    hValue.text = "%.2f".format(storeValueCm)
                    changeUnitBg(this@HeightScreen, cm, ft)
                }
            }

            ft.setOnClickListener {
                if (unit.text != "ft") {
                    unit.text = "ft"
                    isUpdatingScale = true
                    val displayFt = cmToFeet(storeValueCm)
                    scale.setStartingPoint(displayFt)
                    hValue.text = "%.2f".format(displayFt)
                    changeUnitBg(this@HeightScreen, ft, cm)
                }
            }

            scale.setUpdateListener { result ->
                if (isUpdatingScale) {
                    // This callback was triggered by setStartingPoint
                    isUpdatingScale = false
                    return@setUpdateListener
                }
                // Only update internal cm value when user moves the scale
                storeValueCm = if (unit.text == "cm") result else feetToCm(result)
                hValue.text = "%.2f".format(result)
            }



            nextBtn.setOnClickListener {
                nextBtn.setOnClickListener {
                    unitStore = unit.text.toString()

                    val valueToSave = if (unitStore == "cm") storeValueCm else cmToFeet(storeValueCm)

                    // Save value and unit
                    editor.putFloat(Constant.heightValueKey, valueToSave)
                    editor.putString(Constant.heightUnitKey, unitStore)

                    editor.apply()
                    moveActNotFinish(this@HeightScreen, WeightScreen::class.java)
                }



            }

        }


    }


    private fun cmToFeet(cm: Float): Float {
        return cm / 30.48f      // 1 foot = 30.48 cm
    }

    private fun feetToCm(feet: Float): Float {
        return feet * 30.48f
    }


}