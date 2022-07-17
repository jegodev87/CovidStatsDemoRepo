package com.jg2022.demoproject.data.helper

import com.jg2022.demoproject.data.services.ApiService
import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val apiService: ApiService
) {


    suspend fun getCovidStats() = apiService.getCovidData()
}