package com.example.learnandroiddevelopmentbatch2.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnandroiddevelopmentbatch2.R
import com.example.learnandroiddevelopmentbatch2.adapter.MenuBlogAdapter
import com.example.learnandroiddevelopmentbatch2.databinding.ActivityBlogBinding
import com.example.learnandroiddevelopmentbatch2.model.MenuBlogModel

class BlogAct : AppCompatActivity() {

    private lateinit var binding: ActivityBlogBinding

    private lateinit var meneAdp: MenuBlogAdapter
    private lateinit var menuList:ArrayList<MenuBlogModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Dashboard.bottomCardStr="BlogAct"
        Log.i("TAG", "onCreate: ")

        binding.apply {
            back.setOnClickListener {
                finish()
            }


            menuList= ArrayList<MenuBlogModel>()
            menuList.add(MenuBlogModel(R.drawable.bmi,"BMI"))
            menuList.add(MenuBlogModel(R.drawable.bmr,"BMR"))

            menuBlogRv.layoutManager = LinearLayoutManager(this@BlogAct,LinearLayoutManager.HORIZONTAL,false)
//            menuBlogRv.layoutManager = GridLayoutManager(this@BlogAct,2)
            meneAdp= MenuBlogAdapter(menuList)
            menuBlogRv.adapter=meneAdp


        }

    }

}