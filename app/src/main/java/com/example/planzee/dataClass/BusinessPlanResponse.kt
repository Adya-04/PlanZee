package com.example.planzee.dataClass

data class BusinessPlanResponse (
    val result: Result
)

data class Result(
    val business_plan: String,
    val monetization_strategy: String
)