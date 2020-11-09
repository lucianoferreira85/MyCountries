package com.lucianoferreira.mycountries.adapter

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucianoferreira.mycountries.R
import com.lucianoferreira.mycountries.domain.Country
import kotlinx.android.synthetic.main.item_country.view.*
import java.util.*

class CountryAdapter (private val context: Context,
                      private val countries: List<Country>,
                      private val callback: (Country) -> Unit) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private val countryImages: TypedArray by lazy {
        context.resources.obtainTypedArray(R.array.country_images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_country, parent, false)

        val vh = ViewHolder(itemView)
        vh.itemView.setOnClickListener {
            val country = countries[vh.adapterPosition]
            callback(country)
        }

        return vh
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.countryImg.setImageDrawable(countryImages.getDrawable(country.img))
        holder.textCountryName.text = country.name

        Locale.setDefault(Locale.US)
        //val subTotal = String.format("%.2f", food.price.times(food.quantity))
        //holder.textFoodPrice.text = context.getString(R.string.food_subtotal, subTotal)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val countryImg : ImageView = itemView.iconCountry
        val textCountryName: TextView = itemView.textCountryName
        //val textFoodPrice: TextView = itemView.textFoodSubTotal
    }
}