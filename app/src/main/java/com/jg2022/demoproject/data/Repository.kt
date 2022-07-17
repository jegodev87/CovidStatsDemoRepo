package com.jg2022.demoproject.data

import com.jg2022.demoproject.data.helper.ApiHelper
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(
    private val  apiHelper: ApiHelper
) : BaseRepository(){

    suspend fun getCovidDetails() = safeApiCall { apiHelper.getCovidStats() }

}