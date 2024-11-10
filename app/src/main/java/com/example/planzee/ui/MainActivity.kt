// MainActivity.kt
package com.example.planzee.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.planzee.R
import com.example.planzee.dataClass.RequestBody
import com.example.planzee.network.RetrofitAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var brandName: String
    private lateinit var productDesc: String
    private lateinit var businessType: String
    private lateinit var industry: String
    private lateinit var competitor_imgview: ImageView
    private lateinit var businessplan : ImageView
    private lateinit var mvp : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrieve data from FormFill activity
        brandName = intent.getStringExtra("brandName") ?: ""
        productDesc = intent.getStringExtra("productDesc") ?: ""
        businessType = intent.getStringExtra("businessType") ?: ""
        industry = intent.getStringExtra("industry") ?: ""

        competitor_imgview = findViewById(R.id.competitor)
        businessplan = findViewById((R.id.business_plan))
        mvp = findViewById((R.id.mvp))

        competitor_imgview.setOnClickListener {
            // Validate if the form data is available
            if (brandName.isNotEmpty() && productDesc.isNotEmpty() && businessType.isNotEmpty() && industry.isNotEmpty()) {
                val requestBody = RequestBody(brandName, productDesc, businessType, industry)
                fetchCompetitorAnalysis(requestBody)
            } else {
                Toast.makeText(this, "Invalid data. Please refill the form.", Toast.LENGTH_SHORT).show()
            }
        }
        businessplan.setOnClickListener {
            if (brandName.isNotEmpty() && productDesc.isNotEmpty() && businessType.isNotEmpty() && industry.isNotEmpty()) {
                val requestBody = RequestBody(brandName, productDesc, businessType, industry)
                fetchBusinessPlan(requestBody)
            } else {
                Toast.makeText(this, "Invalid data. Please refill the form.", Toast.LENGTH_SHORT).show()
            }
        }

        mvp.setOnClickListener {
            if (brandName.isNotEmpty() && productDesc.isNotEmpty() && businessType.isNotEmpty() && industry.isNotEmpty()) {
                val requestBody = RequestBody(brandName, productDesc, businessType, industry)
                fetchMVP(requestBody)
            } else {
                Toast.makeText(this, "Invalid data. Please refill the form.", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun fetchMVP(requestBody: RequestBody){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitAPI.apiInterface.getMVP(requestBody)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val mvpstepList = response.body()?.result ?: emptyList()
                        Log.d("MainActivity", "MVP Steps List: $mvpstepList")

                        val intent =
                            Intent(this@MainActivity, MVPListActivity::class.java).apply {
                                putParcelableArrayListExtra("mvpList", ArrayList(mvpstepList))
                            }
                        startActivity(intent)
                    } else {
                        Log.e(
                            "MainActivity",
                            "Failed to fetch data: ${response.errorBody()?.string()}"
                        )
                        Toast.makeText(
                            this@MainActivity,
                            "Failed to fetch data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }catch (e: Exception) {
                Log.e("MainActivity", "Error: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
    private fun fetchCompetitorAnalysis(requestBody: RequestBody) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitAPI.apiInterface.getCompetitor(requestBody)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val competitorList = response.body()?.result ?: emptyList()
                        Log.d("MainActivity", "Competitor List: $competitorList")

                        val intent = Intent(this@MainActivity, CompetitorActivity::class.java).apply {
                            putParcelableArrayListExtra("competitorList", ArrayList(competitorList))
                        }
                        startActivity(intent)
                    } else {
                        Log.e("MainActivity", "Failed to fetch data: ${response.errorBody()?.string()}")
                        Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun fetchBusinessPlan(requestBody: RequestBody) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Log the request body manually
                Log.d("MainActivity", "Request Body (Business Plan): ${requestBody.toString()}")

                // Call the API
                val response1 = RetrofitAPI.apiInterface.getBusinessPlan(requestBody)
                withContext(Dispatchers.Main) {
                    if (response1.isSuccessful) {
                        val businessPlanResponse = response1.body()
                        if (businessPlanResponse != null) {
                            Log.d("MainActivity", "Response body: $businessPlanResponse")
                            val businessPlan = businessPlanResponse.business_plan
                            val monetizationStrategy = businessPlanResponse.monetization_strategy

                            Log.d("MainActivity", "Business Plan: $businessPlan")
                            Log.d("MainActivity", "Monetization Strategy: $monetizationStrategy")

                            val intent = Intent(this@MainActivity, BusinessPlanActivity::class.java).apply {
                                putExtra("businessPlan", businessPlan)
                                putExtra("monetizationStrategy", monetizationStrategy)
                            }
                            startActivity(intent)
                        } else {
                            Log.e("MainActivity", "No business plan data available.")
                            Toast.makeText(this@MainActivity, "No business plan data available.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.e("MainActivity", "Failed to fetch business plan: ${response1.errorBody()?.string()}")
                        Toast.makeText(this@MainActivity, "Failed to fetch business plan", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
