package com.example.foododeringsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchFoodAdapter(private val foodList: List<SearchFood>) :
    RecyclerView.Adapter<SearchFoodAdapter.SearchFoodViewHolder>() {

    class SearchFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFood: ImageView = itemView.findViewById(R.id.imgFood)
        val tvFoodName: TextView = itemView.findViewById(R.id.tvFoodName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return SearchFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchFoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.imgFood.setImageResource(food.imageResId)
        holder.tvFoodName.text = food.name
        holder.tvPrice.text = food.price
    }

    override fun getItemCount(): Int = foodList.size
}