// CompetitorActivity.kt
package com.example.planzee.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planzee.CompetitorAdapter
import com.example.planzee.R
import com.example.planzee.dataClass.Competitor

class CompetitorActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var competitorAdapter: CompetitorAdapter
    private lateinit var competitorList: List<Competitor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competitor)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrieve the competitor list from the intent
        competitorList = intent.getParcelableArrayListExtra("competitorList") ?: emptyList()

        competitorAdapter = CompetitorAdapter(competitorList)
        recyclerView.adapter = competitorAdapter
    }
}
