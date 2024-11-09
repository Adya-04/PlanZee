package com.example.planzee.network

import com.example.planzee.dataClass.BusinessPlanResponse
import com.example.planzee.dataClass.CompetitorResponseBody
import com.example.planzee.dataClass.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {
    @POST("competitor")
    suspend fun getCompetitor(@Body requestBody: RequestBody): Response<CompetitorResponseBody>

    @POST("business-plan")
    suspend fun getBusinessPlan(@Body requestBody: RequestBody): Response<BusinessPlanResponse>

}