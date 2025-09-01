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
import com.example.learnandroiddevelopmentbatch2.util.moveAct

class SplashAct : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Share PreF GET OR iNITIALIZE
        val pref=getSharedPreferences("HeightScreenPref",MODE_PRIVATE)
         var heightValue=pref.getFloat("heightValue",0.5f)
        var unit=pref.getString("unit","cm")

        Log.i("TAG", "onCreate: ${heightValue}--${unit}")


        //scope function
        // click lister
        /*Intent
         -> One Act to Another Act Explicit Intent
         -> Out Of App Implicit Intent -> code android studio button dialer
         */
        // Thread or Handler


        android.os.Handler(Looper.getMainLooper()).postDelayed(
            {
                binding.title.text="Shukria For waiting so move to next screen"
                binding.progressBar.visibility= View.GONE
                binding.startBtn.visibility= View.VISIBLE
            },
            3000
        )
//
//        android.os.Handler(Looper.getMainLooper()).postDelayed(
//            {
//                //TasK Perform
//
//                var intent= Intent(this@SplashAct, HeightScreen::class.java)
//                startActivity(intent)
//                finish()
//            },
//            7000
//        )

        binding.apply {
            startBtn.setOnClickListener {
                // Intent(CurrentAct,TargetAct)
                moveAct(this@SplashAct, GenderAct::class.java)

                //single line work
                //startActivity(Intent(this@SplashAct, HeightScreen::class.java))
            }
        }


    }
}