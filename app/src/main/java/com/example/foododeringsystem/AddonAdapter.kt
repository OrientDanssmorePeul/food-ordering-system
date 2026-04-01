package com.example.foododeringsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class AddonClass(
    val header: String
)
data class AddonOption(
    val name: String,
    val price: Double,
    var isSelected: Boolean = false
)

class AddonAdapter(private val options: List<AddonOption>) :
    RecyclerView.Adapter<AddonAdapter.AddonViewHolder>() {

    // 1. The ViewHolder: This finds the views inside your item_text.xml
    class AddonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.addonName)
        val priceText: TextView = view.findViewById(R.id.addonPrice)
        val checkBox: CheckBox = view.findViewById(R.id.addonCheckBox)
    }

    sealed class AddonListItem {
        data class Header(val title: String) : AddonListItem()
        data class Option(val data: AddonOption) : AddonListItem()
    }

    // 2. onCreateViewHolder: This "inflates" the XML layout for each row
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_text, parent, false)
        return AddonViewHolder(view)
    }

    // 3. onBindViewHolder: This puts the REAL data into the views
    override fun onBindViewHolder(holder: AddonViewHolder, position: Int) {
        val currentItem = options[position]

        holder.nameText.text = currentItem.name
        holder.priceText.text = "RM ${currentItem.price}"
        holder.checkBox.isChecked = currentItem.isSelected

        // Handle clicks so the app remembers what you checked
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            currentItem.isSelected = isChecked
        }
    }

    // 4. Tells the list how many items to show
    override fun getItemCount() = options.size
}