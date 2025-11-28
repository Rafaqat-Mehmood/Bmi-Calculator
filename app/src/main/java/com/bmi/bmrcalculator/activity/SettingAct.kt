package com.bmi.bmrcalculator.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bmi.bmrcalculator.R
import com.bmi.bmrcalculator.databinding.ActivitySettingBinding
import com.bmi.bmrcalculator.util.moreApps
import com.bmi.bmrcalculator.util.moveAct
import com.bmi.bmrcalculator.util.rateUs
import com.bmi.bmrcalculator.util.shareApp

class SettingAct : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.apply {
            back.setOnClickListener {
                finish()
            }

            backToScreen.setOnClickListener {
                moveAct(this@SettingAct, GenderAct::class.java)
            }

            notification.setOnClickListener {
                Toast.makeText(this@SettingAct, "Coming Soon", Toast.LENGTH_SHORT).show()
            }
            rateUs.setOnClickListener {
                rateUs()

            }
            shareApp.setOnClickListener {
                shareApp()

            }
            moreApps.setOnClickListener {
                moreApps()

            }
            privacyPolicy.setOnClickListener {
             var intent= Intent(this@SettingAct, PrivacyPolicy::class.java)
             startActivity(intent)
            }

        }
    }
}