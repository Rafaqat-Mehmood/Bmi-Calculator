package com.example.learnandroiddevelopmentbatch2.activity

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnandroiddevelopmentbatch2.R
import com.example.learnandroiddevelopmentbatch2.databinding.ActivitySplashBinding
import com.example.learnandroiddevelopmentbatch2.util.Constant
import com.example.learnandroiddevelopmentbatch2.util.moveAct

class SplashAct : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Share PreF GET OR iNITIALIZE
        val pref = getSharedPreferences("DbRef", MODE_PRIVATE)


        android.os.Handler(Looper.getMainLooper()).postDelayed(
            {
                binding.title.text = "Shukria For Waiting"
                binding.progressBar.visibility = View.GONE
                binding.startBtn.visibility = View.VISIBLE
            },
            3000
        )

        binding.apply {
            startBtn.setOnClickListener {
                if (pref.getBoolean(Constant.finishFirstTime, false)) {
                    moveAct(this@SplashAct, Dashboard::class.java)

                } else {
                    moveAct(this@SplashAct, GenderAct::class.java)

                }


            }
        }


    }
}