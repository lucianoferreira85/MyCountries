package com.lucianoferreira.mycountries.listener


import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.lucianoferreira.mycountries.R
import com.lucianoferreira.mycountries.adapter.CountryAdapter
import com.lucianoferreira.mycountries.domain.Country

class SwipeListener(
    private val context: Context,
    private val adapter: CountryAdapter,
    private val countries: ArrayList<Country>
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val country = countries[viewHolder.adapterPosition]

        countries.remove(country)
        adapter.notifyItemRemoved(viewHolder.adapterPosition)

        Toast.makeText(
            context,
            context.getString(R.string.country_removed, country.name),
            Toast.LENGTH_LONG
        ).show()
    }
}