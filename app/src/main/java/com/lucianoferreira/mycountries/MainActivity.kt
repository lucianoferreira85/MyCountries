package com.lucianoferreira.mycountries

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucianoferreira.mycountries.adapter.CountryAdapter
import com.lucianoferreira.mycountries.domain.Country
import com.lucianoferreira.mycountries.listener.SwipeListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    companion object {
        const val MAIN_ACTIVITY_COUNTRY_EXTRA_ID = "COUNTRY_EXTRA_ID"
        const val LAUNCH_SECOND_ACTIVITY_REQUEST_CODE = 1
    }

    private var mCountryList = arrayListOf<Country>(

    )

    private val mCountryAdapter = CountryAdapter(this, mCountryList, this::onCountryClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        pressAddButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LAUNCH_SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val country = data?.getParcelableExtra<Country>(CountryDetailMainActivity.RESULT)
                if (country != null) {
                    mCountryList[country.id] = country
                    mCountryAdapter.notifyItemChanged(country.id)
                }
            }
        }
    }

    private fun onCountryClickListener(country: Country) {
        val intent = Intent(this, CountryDetailMainActivity::class.java)
        intent.putExtra(MAIN_ACTIVITY_COUNTRY_EXTRA_ID, country)

        startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY_REQUEST_CODE)
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mCountryAdapter
        }

        val swipeListener = SwipeListener(this@MainActivity, mCountryAdapter, mCountryList)
        ItemTouchHelper(swipeListener).attachToRecyclerView(recyclerView)
    }

    private fun pressAddButton() {
        btnAddCountry.setOnClickListener {
            val type = countriesSpinner.selectedItem.toString()
            val name = editCountryName.text.toString()

            if (name.isNotEmpty()) {
                //val price = Random.nextDouble(1.0, 30.0)
                val typeIndex = resources.getStringArray(R.array.country_types).indexOf(type)
                val country = Country (mCountryList.lastIndex + 1, name, 1, typeIndex)

                mCountryList.add(country)
                mCountryAdapter.notifyItemInserted(mCountryList.lastIndex)

                editCountryName.text.clear()
                editCountryName.clearFocus()
            } else {
                Toast.makeText(this, getString(R.string.country_empty), Toast.LENGTH_SHORT).show()
            }
        }
    }
}