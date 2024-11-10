package com.example.planzee.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.planzee.R

class BusinessPlanActivity : AppCompatActivity() {

    private lateinit var businessPlanText: TextView
    private lateinit var monetizationStrategyText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_plan)

        // Initialize the TextViews
        businessPlanText = findViewById(R.id.businessPlanText)
        monetizationStrategyText = findViewById(R.id.monetizationStrategyText)

        // Retrieve data passed from MainActivity
        val businessPlan = intent.getStringExtra("businessPlan") ?: "No business plan available."
        val monetizationStrategy = intent.getStringExtra("monetizationStrategy") ?: "No monetization strategy available."

        // Display the retrieved data
        businessPlanText.text = "Business Plan: $businessPlan"
        monetizationStrategyText.text = "Monetization Strategy: $monetizationStrategy"
    }
}
