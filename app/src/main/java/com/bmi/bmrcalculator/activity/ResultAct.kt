package com.bmi.bmrcalculator.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bmi.bmrcalculator.R
import com.bmi.bmrcalculator.databinding.ActivityResultBinding

class ResultAct : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    var bmiValue=0.0
    var bmrValue=0.0
    var category=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.apply {

            // Data Get and Set in TextView at Run time
            bmi.text=intent.getStringExtra("name")
            if (intent.getStringExtra("name")=="Body Mass Index") {
                bmiValue=intent.getDoubleExtra("bmi",18.5)
                category=intent.getStringExtra("categories").toString()
                resultStatus.text=category
                result.text="%.2f".format(bmiValue)
                Log.i("TAG", "Result: $bmi")

            }
            else
            {
                bmrValue=intent.getDoubleExtra("bmr",18.5)
                resultStatus.visibility= View.GONE
                status.visibility=View.GONE

                icon.visibility=View.GONE
                result.text="%.2f".format(bmrValue)
            }

            backIcon.setOnClickListener {
                finish()
            }




        }
    }
}