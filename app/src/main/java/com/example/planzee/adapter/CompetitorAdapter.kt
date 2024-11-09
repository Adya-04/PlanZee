// CompetitorAdapter.kt
package com.example.planzee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planzee.dataClass.Competitor

class CompetitorAdapter(private val competitors: List<Competitor>) :
    RecyclerView.Adapter<CompetitorAdapter.CompetitorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_competitor, parent, false)
        return CompetitorViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompetitorViewHolder, position: Int) {
        val competitor = competitors[position]
        holder.bind(competitor)
    }

    override fun getItemCount() = competitors.size

inner class CompetitorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val competitorName: TextView = itemView.findViewById(R.id.tvCompetitorName)
    private val strengths: TextView = itemView.findViewById(R.id.tvStrengths)
    private val weaknesses: TextView = itemView.findViewById(R.id.tvWeaknesses)
    private val marketShare: TextView = itemView.findViewById(R.id.tvMarketShare)
    private val uniqueness: TextView = itemView.findViewById(R.id.tvUniqueness)

    fun bind(competitor: Competitor) {
        competitorName.text = competitor.competitor_name
        strengths.text = competitor.strengths
        weaknesses.text = competitor.weaknesses
        marketShare.text = competitor.market_share
        uniqueness.text = competitor.uniqueness
    }
}
}
