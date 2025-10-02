package com.example.learnandroiddevelopmentbatch2.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnandroiddevelopmentbatch2.R
import com.example.learnandroiddevelopmentbatch2.databinding.ActivityHeightScreenBinding
import com.example.learnandroiddevelopmentbatch2.util.Constant
import com.example.learnandroiddevelopmentbatch2.util.MyScaleView
import com.example.learnandroiddevelopmentbatch2.util.changeUnitBg
import com.example.learnandroiddevelopmentbatch2.util.moveActNotFinish
import com.example.learnandroiddevelopmentbatch2.util.onViewUpdateListener
import kotlin.math.roundToInt

class HeightScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHeightScreenBinding

    companion object{
        var unitStore=""
        var storeValue=165.00f

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
        val pref=getSharedPreferences("DbRef",MODE_PRIVATE)

        // Create editor
        val editor=pref.edit()




        binding.apply {
            backIcon.setOnClickListener {
                // Activity Not Recreate Activity
                finish()

            }

            cm.setOnClickListener {
                changeUnitBg(this@HeightScreen,cm,ft)
                unit.text = "cm"
            }
            ft.setOnClickListener {
                changeUnitBg(this@HeightScreen,ft,cm)
                unit.text = "ft"

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
                editor.putFloat(Constant.heightValueKey,storeValue)
                editor.putString(Constant.heightUnitKey,unitStore)

                //Apply Changes or save
                editor.apply()
                moveActNotFinish(this@HeightScreen, WeightScreen::class.java)



            }

        }



    }

}