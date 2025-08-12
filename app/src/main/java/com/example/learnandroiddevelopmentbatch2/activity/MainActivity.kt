package com.example.learnandroiddevelopmentbatch2.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnandroiddevelopmentbatch2.R
import com.example.learnandroiddevelopmentbatch2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //variables declare
    // Button or view declare
    // service or boradcast declare
    // method declare
    // result declare

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize any thing
        // logic for any thing


        // MVC   Model View Controller    Small Project -> printer -> translator -pdf - voice app -> tracker ->Testing Complex
        // MVP  Model View Presenter
        // MVVM Model View ViewModel      Large Complex Project -> Ubber Bykia Food deliver expnese manager  -> Testing best single modle
        // MVI Model View Intent

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