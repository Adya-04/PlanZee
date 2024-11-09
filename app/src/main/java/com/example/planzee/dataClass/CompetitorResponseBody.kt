package com.example.planzee.dataClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CompetitorResponseBody (
    val result: List<Competitor>
)
@Parcelize
data class Competitor(
    val competitor_name: String,
    val strengths: String,
    val weaknesses: String,
    val market_share: String,
    val uniqueness: String
) : Parcelable
