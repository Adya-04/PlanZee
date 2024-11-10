package com.example.planzee.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planzee.R
import com.example.planzee.adapter.MVPListAdapter
import com.example.planzee.dataClass.Step

class MVPListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mvpListAdapter: MVPListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvplist)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val mvpList:List<Step> = intent.getParcelableArrayListExtra<Step>("mvpList") ?: emptyList()
        mvpListAdapter = MVPListAdapter(mvpList)
        recyclerView.adapter = mvpListAdapter
    }
}
