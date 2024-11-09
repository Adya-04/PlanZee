// MainActivity.kt
package com.example.planzee.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.planzee.R
import com.example.planzee.dataClass.RequestBody
import com.example.planzee.network.RetrofitAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormFill : AppCompatActivity() {

    private lateinit var brandNameEditText: EditText
    private lateinit var productDescEditText: EditText
    private lateinit var businessTypeEditText: EditText
    private lateinit var industryEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fill_form)

        // Initialize views
        brandNameEditText = findViewById(R.id.brand_name)
        productDescEditText = findViewById(R.id.product_description)
        businessTypeEditText = findViewById(R.id.business_type)
        industryEditText = findViewById(R.id.industry)
        submitButton = findViewById(R.id.submit_bt)
        progressBar = findViewById(R.id.progress_bar)

        submitButton.setOnClickListener {
            val brandName = brandNameEditText.text.toString()
            val productDesc = productDescEditText.text.toString()
            val businessType = businessTypeEditText.text.toString()
            val industry = industryEditText.text.toString()

            progressBar.visibility = View.VISIBLE

            if (brandName.isNotEmpty() && productDesc.isNotEmpty() && businessType.isNotEmpty() && industry.isNotEmpty()) {
                // Call API in a coroutine
//                fetchCompetitorAnalysis(RequestBody(brandName, productDesc, businessType, industry))
                // Log the request body before making the API call
                val requestBody = RequestBody(brandName, productDesc, businessType, industry)
                Log.d("MainActivity", "RequestBody: $requestBody")

                // Call API in a coroutine
                fetchCompetitorAnalysis(requestBody)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchCompetitorAnalysis(requestBody: RequestBody) {
        CoroutineScope(Dispatchers.IO).launch {
            try{
            val response = RetrofitAPI.apiInterface.getCompetitor(requestBody)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val competitorList = response.body()?.result

                    // Log the result for debugging
                    Log.d("MainActivity", "Competitor List: $competitorList")

                    val intent = Intent(this@FormFill, CompetitorActivity::class.java).apply {
                        putParcelableArrayListExtra("competitorList", ArrayList(competitorList ?: emptyList()))
                    }
                    startActivity(intent)
                } else {
                    Log.e("MainActivity", "Failed to fetch data: ${response.errorBody()?.string()}")
                    Toast.makeText(this@FormFill, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            }catch (e: Exception) {
                Log.e("MainActivity", "retrieving failed: ${e.message}")
            }
        }
    }
}
