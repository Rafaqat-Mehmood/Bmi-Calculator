package com.example.learnandroiddevelopmentbatch2.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnandroiddevelopmentbatch2.R

class AgeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_age_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pref=getSharedPreferences("DbRef",MODE_PRIVATE)
        var heightValuex=pref.getFloat("heightValue",0.5f)
        var unitx=pref.getString("unit","cm")
        var weightunitx=pref.getString("weightUnit","kg")
        var weightValue=pref.getFloat("weightValue",4.5f)


        Log.i("TAG", "onCreate: ${heightValuex}--${unitx}")
        Log.i("TAG", "onCreate: ${weightunitx}--${weightValue}")


    }
}