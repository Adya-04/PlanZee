package com.example.planzee.dataClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MVPResponse(
    val result: List<Step>
)
@Parcelize
data class Step(
    val step_no: Int,
    val title: String,
    val description: String
): Parcelable
