package com.example.planzee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planzee.R
import com.example.planzee.dataClass.Step


class MVPListAdapter(private val mvpSteps: List<Step>) :
    RecyclerView.Adapter<MVPListAdapter.MVPViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MVPViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mvplist, parent, false)
        return MVPViewHolder(view)
    }

    override fun onBindViewHolder(holder: MVPViewHolder, position: Int) {
        val step = mvpSteps[position]
        holder.bind(step)
    }

    override fun getItemCount() = mvpSteps.size

    inner class MVPViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stepNo: TextView = itemView.findViewById(R.id.step_no)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)

        fun bind(step: Step) {
            stepNo.text = "Step No: ${step.step_no}"
            title.text = step.title
            description.text = step.description
        }
    }
}