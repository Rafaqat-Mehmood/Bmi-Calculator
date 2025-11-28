package com.bmi.bmrcalculator.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bmi.bmrcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // initialization
        // Logic Building Work

       binding.apply {
           clickMe.setOnClickListener {
               Log.i(TAG, "onCreate: ")
               Toast.makeText(it.context, "Hello", Toast.LENGTH_SHORT).show()
           }

           clickMe2.setOnClickListener {
               Log.i(TAG, "onCreate: ")
               Toast.makeText(this@MainActivity, "Hello", Toast.LENGTH_SHORT).show()
           }
       }


    }


}