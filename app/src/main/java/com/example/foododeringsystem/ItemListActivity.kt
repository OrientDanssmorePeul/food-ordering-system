package com.example.foododeringsystem

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemListActivity : AppCompatActivity() {

    private lateinit var searchFoodList: MutableList<SearchFood>
    private lateinit var filteredList: MutableList<SearchFood>
    private lateinit var searchFoodAdapter: SearchFoodAdapter

    private var selectedCategory = "All"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.item_list)

        val etSearch = findViewById<EditText>(R.id.etSearch)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFood)

        val tvAll = findViewById<TextView>(R.id.tvAll)
        val tvBurger = findViewById<TextView>(R.id.tvBurger)
        val tvSnack = findViewById<TextView>(R.id.tvSnack)
        val tvDrink = findViewById<TextView>(R.id.tvDrink)

        searchFoodList = mutableListOf(
            SearchFood("Burger", "RM 12.00", R.drawable.burger, "Burger"),
            SearchFood("Cheese Burger", "RM 14.00", R.drawable.cheeseburger, "Burger"),
            SearchFood("Fish Burger", "RM 13.90", R.drawable.fish_burger, "Burger"),
            SearchFood("Nugget", "RM 9.90", R.drawable.ic_nunget, "Snack"),
            SearchFood("Hash Brown", "RM 6.90", R.drawable.images, "Snack"),
            SearchFood("Coke", "RM 4.00", R.drawable.images2, "Drink")
        )

        filteredList = searchFoodList.toMutableList()

        searchFoodAdapter = SearchFoodAdapter(filteredList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = searchFoodAdapter

        tvAll.setOnClickListener {
            selectedCategory = "All"
            updateCategoryUI(tvAll, tvBurger, tvSnack, tvDrink)
            filterFood(etSearch.text.toString())
        }

        tvBurger.setOnClickListener {
            selectedCategory = "Burger"
            updateCategoryUI(tvAll, tvBurger, tvSnack, tvDrink)
            filterFood(etSearch.text.toString())
        }

        tvSnack.setOnClickListener {
            selectedCategory = "Snack"
            updateCategoryUI(tvAll, tvBurger, tvSnack, tvDrink)
            filterFood(etSearch.text.toString())
        }

        tvDrink.setOnClickListener {
            selectedCategory = "Drink"
            updateCategoryUI(tvAll, tvBurger, tvSnack, tvDrink)
            filterFood(etSearch.text.toString())
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterFood(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun filterFood(keyword: String) {
        filteredList.clear()
        val searchText = keyword.lowercase()

        for (food in searchFoodList) {
            val matchCategory = selectedCategory == "All" || food.category == selectedCategory
            val matchKeyword = food.name.lowercase().contains(searchText)

            if (matchCategory && matchKeyword) {
                filteredList.add(food)
            }
        }

        searchFoodAdapter.notifyDataSetChanged()
    }

    private fun updateCategoryUI(
        tvAll: TextView,
        tvBurger: TextView,
        tvSnack: TextView,
        tvDrink: TextView
    ) {
        tvAll.setBackgroundResource(
            if (selectedCategory == "All") R.drawable.category_selected_bg
            else R.drawable.category_unselected_bg
        )

        tvBurger.setBackgroundResource(
            if (selectedCategory == "Burger") R.drawable.category_selected_bg
            else R.drawable.category_unselected_bg
        )

        tvSnack.setBackgroundResource(
            if (selectedCategory == "Snack") R.drawable.category_selected_bg
            else R.drawable.category_unselected_bg
        )

        tvDrink.setBackgroundResource(
            if (selectedCategory == "Drink") R.drawable.category_selected_bg
            else R.drawable.category_unselected_bg
        )

        tvAll.setTextColor(if (selectedCategory == "All") getColor(android.R.color.white) else 0xFF222222.toInt())
        tvBurger.setTextColor(if (selectedCategory == "Burger") getColor(android.R.color.white) else 0xFF222222.toInt())
        tvSnack.setTextColor(if (selectedCategory == "Snack") getColor(android.R.color.white) else 0xFF222222.toInt())
        tvDrink.setTextColor(if (selectedCategory == "Drink") getColor(android.R.color.white) else 0xFF222222.toInt())
    }
}