package com.example.learnandroiddevelopmentbatch2.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnandroiddevelopmentbatch2.R
import com.example.learnandroiddevelopmentbatch2.databinding.ActivityHeightScreenBinding

class HeightScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHeightScreenBinding
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

        binding.apply {
            cm.setOnClickListener {
                changeUnitBg(cm,ft)
                unit.text = "cm"
            }
            ft.setOnClickListener {
                changeUnitBg(ft,cm)
                unit.text = "ft"

            }
        }



    }

    private fun changeUnitBg(cm: TextView, ft:TextView) {
        cm.setBackgroundResource(R.drawable.ftcm_fill_bg)
        ft.setBackgroundResource(R.drawable.ftcm_whilte_fill_bg)
        cm.setTextColor(resources.getColor(R.color.white))
        ft.setTextColor(resources.getColor(R.color.common__light_color))

    }
}