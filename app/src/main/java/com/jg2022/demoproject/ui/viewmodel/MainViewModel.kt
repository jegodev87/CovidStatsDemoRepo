package com.jg2022.demoproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jg2022.demoproject.data.NetworkResult
import com.jg2022.demoproject.data.Repository
import com.jg2022.demoproject.model.DistrictData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
   private val _covidResponse = MutableLiveData<NetworkResult<HashMap<String, DistrictData>>>()
    val covideResponse : LiveData<NetworkResult<HashMap<String, DistrictData>>> =_covidResponse



     fun fetchCovidStats() = viewModelScope.launch {
        val apiResponse = repository.getCovidDetails()
        _covidResponse.postValue(apiResponse)
    }
}