package com.example.learnandroiddevelopmentbatch2

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnandroiddevelopmentbatch2.databinding.ActivityBlogDetailBinding
import com.example.learnandroiddevelopmentbatch2.model.SubMenuBlogModel
import org.json.JSONArray
import java.io.BufferedReader

class BlogDetailAct : AppCompatActivity() {
    private lateinit var bmiJsonArray: JSONArray
    private lateinit var bmrJsonArray: JSONArray

    private lateinit var binding: ActivityBlogDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityBlogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val model = intent.getParcelableExtra<SubMenuBlogModel>("model")

        // Load JSON once
        val bmiJsonString = readFromRaw(R.raw.bmi_blog)
        val bmrJsonString = readFromRaw(R.raw.bmr_blog)
        bmiJsonArray = JSONArray(bmiJsonString)
        bmrJsonArray = JSONArray(bmrJsonString)



        binding.apply {
            back.setOnClickListener {
                finish()
            }
            if (model != null) {

                blogIcon.setImageResource(model.image)
//                blogTime.text=model.time
//                blogDescription.text=model.mainHeading

                   showUserById(model.id)
//                Log.i("TAG", "Time: ${model.time}")
//                Log.i("TAG", "Heading: ${model.mainHeading}")
            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun showUserById(id: Int) {
        for (i in 0 until bmiJsonArray.length()) {
            val user = bmiJsonArray.getJSONObject(i)
            if (user.getInt("id") == id) {
                val title = user.getString("title")
                val readTime = user.getString("read_time")
                val description = user.getString("description")

                binding.blogTime.text = Html.fromHtml(readTime, Html.FROM_HTML_MODE_LEGACY)
                binding.blogDescription.text = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY)

                return
            }
        }
    }



    private fun readFromRaw(resourceId: Int): String {
        val inputStream = resources.openRawResource(resourceId)
        val reader = BufferedReader(inputStream.reader())
        return reader.use { it.readText() }
    }
}