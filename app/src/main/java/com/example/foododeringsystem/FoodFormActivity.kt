package com.example.foododeringsystem

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class FoodFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.food_form_activity)
        val recyclerView = findViewById<RecyclerView>(R.id.addonsRecyclerView)

        val myData = listOf(
            AddonOption("Extra Cheese", 50.0),
            AddonOption("Mushrooms", 30.0),
            AddonOption("Beef Pepperoni", 120.0),
            AddonOption("Bell Peppers", 20.0),
            AddonOption("Bell Peppers", 20.0),
            AddonOption("Bell Peppers", 20.0),
            AddonOption("Bell Peppers", 20.0),
            AddonOption("Bell Peppers", 20.0),
            AddonOption("Bell Peppers", 20.0),
            AddonOption("Bell Peppers", 20.0),
            AddonOption("Bell Peppers", 20.0)
        )

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. Attach the Adapter
        val adapter = AddonAdapter(myData)
        recyclerView.adapter = adapter
    }
}