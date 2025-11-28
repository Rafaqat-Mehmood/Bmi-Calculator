package com.bmi.bmrcalculator.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bmi.bmrcalculator.BlogDetailAct
import com.bmi.bmrcalculator.R
import com.bmi.bmrcalculator.adapter.MenuBlogAdapter
import com.bmi.bmrcalculator.adapter.SubMenuBlogAdapter
import com.bmi.bmrcalculator.callback.MainMenuListener
import com.bmi.bmrcalculator.callback.SubMenuListener
import com.bmi.bmrcalculator.databinding.ActivityBlogBinding
import com.bmi.bmrcalculator.model.MenuBlogModel
import com.bmi.bmrcalculator.model.SubMenuBlogModel

class BlogAct : AppCompatActivity(), MainMenuListener, SubMenuListener {

    private lateinit var binding: ActivityBlogBinding

    private lateinit var meneAdp: MenuBlogAdapter
    private lateinit var menuList:ArrayList<MenuBlogModel>

    private lateinit var subMeneAdp: SubMenuBlogAdapter
    private lateinit var subMenuList:ArrayList<SubMenuBlogModel>

    var storeName="BMI"
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
            meneAdp= MenuBlogAdapter(menuList,this@BlogAct)
            menuBlogRv.adapter=meneAdp


           setAdapter("BMI")


        }

    }

    override fun onMenuClick(model: MenuBlogModel) {
        Log.i("TAG", "Actiivty: "+model.title)
        Log.i("TAG", "Actiivty: "+model.image)

        setAdapter(model.title)
        storeName=model.title


    }

    override fun onMenuClick(model: SubMenuBlogModel) {
        Log.i("TAG", "Actiivty: "+model.time)

        var intent= Intent(this@BlogAct, BlogDetailAct::class.java)
        intent.putExtra("model",model)
        intent.putExtra("storeName",storeName)
        startActivity(intent)
    }

    private fun setAdapter(title: String) {
        binding.apply {
            subMenuList = ArrayList<SubMenuBlogModel>()

            if (title=="BMI") {
                subMenuList.add(SubMenuBlogModel(
                        1,"4 Min",
                        "BMI Explained What \n It Means for Your Health",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        2,"5 Min",
                        "Simple Habits for a \n Healthier You",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        3,"6 Min",
                        "BMI and Obesity \n Understanding the Risks",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        4,"7 Min",
                        "Is BMI Accurate? \n Pros & Cons Revealed",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        5,"5 Min",
                        "How Diet & Exercise \n Impact Your BMI",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        6,"6 Min",
                        "BMI for Women vs Men \n key difference",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        7,"7 Min",
                        "BMI & Health Risks \n What`s the Link",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        8,"5 Min",
                        "Can you be healthy \n with a high BMI",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        9,"6 Min",
                        "Fast Ways to Improve  \n Your BMI Naturally",
                        R.drawable.see_more_icon
                    )
                )
            }
            else
            {
                subMenuList.add(SubMenuBlogModel(
                    1,"4 Min",
                    "BMR Explained What \nIt Means for Your Health",
                    R.drawable.see_more_icon
                )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        2,"5 Min",
                        "How to Accurately \n Calculate Your BMR",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        3,"6 Min",
                        "BMR vs TDEE \n Key Differences Explained",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        4,"7 Min",
                        "Why BMR Matters \n for weight management",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        5,"5 Min",
                        "Boost your BMR \n Simple Daily Habits",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        6,"6 Min",
                        "BMR & Metabolism  \n what`s the Link",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        7,"5 Min",
                        "How Age Affects  \n Your BMR & Weights",
                        R.drawable.see_more_icon
                    )
                )
                subMenuList.add(
                    SubMenuBlogModel(
                        8,"6 Min",
                        "The Role of BMR in  \\n Weight Loss & Gain",
                        R.drawable.see_more_icon
                    )
                )
            }

            BlogDetailRv.layoutManager = LinearLayoutManager(this@BlogAct,LinearLayoutManager.VERTICAL,false)
//            menuBlogRv.layoutManager = GridLayoutManager(this@BlogAct,2)
            subMeneAdp= SubMenuBlogAdapter(subMenuList,this@BlogAct)
            BlogDetailRv.adapter=subMeneAdp
        }
    }


}