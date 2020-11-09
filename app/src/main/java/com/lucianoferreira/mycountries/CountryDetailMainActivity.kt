package com.lucianoferreira.mycountries

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucianoferreira.mycountries.domain.Country
import kotlinx.android.synthetic.main.activity_country_detail_main.*
import java.util.*

class CountryDetailMainActivity : AppCompatActivity() {
    companion object {
        const val RESULT = "result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail_main)

        val country = intent.extras?.getParcelable<Country>(MainActivity.MAIN_ACTIVITY_COUNTRY_EXTRA_ID)

        if (country != null) {
            val images = applicationContext.resources.obtainTypedArray(R.array.country_images)
            imgCountryDetail.setImageDrawable(images.getDrawable(country.img))
            images.recycle()

            lblCountryName.text = country.name

            Locale.setDefault(Locale.US)
            edtCountryQt.setText(country.quantity.toString())
        }

        handlePressSave(country)
    }

    private fun handlePressSave(country: Country?) {
        btnSave.setOnClickListener {

            lblCountryName.text

            val intentBack = Intent(this, MainActivity::class.java)
            intentBack.putExtra(RESULT, country)
            setResult(RESULT_OK, intentBack)

            finish()
        }
    }
}