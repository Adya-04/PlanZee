// FormFill.kt
package com.example.planzee.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.planzee.R

class FormFill : AppCompatActivity() {

    private lateinit var brandNameEditText: EditText
    private lateinit var productDescEditText: EditText
    private lateinit var businessTypeEditText: EditText
    private lateinit var industryEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fill_form)

        // Initialize views
        brandNameEditText = findViewById(R.id.brand_name)
        productDescEditText = findViewById(R.id.product_description)
        businessTypeEditText = findViewById(R.id.business_type)
        industryEditText = findViewById(R.id.industry)
        submitButton = findViewById(R.id.submit_bt)

        submitButton.setOnClickListener {
            val brandName = brandNameEditText.text.toString()
            val productDesc = productDescEditText.text.toString()
            val businessType = businessTypeEditText.text.toString()
            val industry = industryEditText.text.toString()

            if (brandName.isNotEmpty() && productDesc.isNotEmpty() && businessType.isNotEmpty() && industry.isNotEmpty()) {
                // Pass entered data to MainActivity
                val intent = Intent(this@FormFill, MainActivity::class.java).apply {
                    putExtra("brandName", brandName)
                    putExtra("productDesc", productDesc)
                    putExtra("businessType", businessType)
                    putExtra("industry", industry)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
