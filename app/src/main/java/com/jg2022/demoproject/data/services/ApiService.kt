package com.jg2022.demoproject.data.services

import com.jg2022.demoproject.model.DistrictData
import com.jg2022.demoproject.utils.Constants
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET(Constants.state_district_wise)
    suspend fun getCovidData(): Response<HashMap<String, DistrictData>>


}