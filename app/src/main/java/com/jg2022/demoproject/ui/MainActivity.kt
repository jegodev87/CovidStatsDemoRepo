package com.jg2022.demoproject.ui

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.jg2022.demoproject.R
import com.jg2022.demoproject.databinding.ActivityMainBinding
import com.jg2022.demoproject.data.NetworkResult
import com.jg2022.demoproject.model.DistrictData
import com.jg2022.demoproject.model.StateDetails
import com.jg2022.demoproject.ui.adapter.CustomExpandableListAdapter
import com.jg2022.demoproject.ui.viewmodel.MainViewModel
import com.jg2022.demoproject.utils.*
import com.jg2022.demoproject.utils.comparators.SortByConfirmedCaseComparator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var _binding: ActivityMainBinding
    private var adapter: CustomExpandableListAdapter? = null
    private var stateDetailsList = ArrayList<StateDetails>()
    private var mCovidStatsMap = hashMapOf<String, DistrictData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)


        setStatusBarColor()
        setUpCovidStatsRecyclerview()
        observeCovidStatsResponse()
        if (hasInternet()){
            mainViewModel.fetchCovidStats()
           showNotificationText(getString(R.string.fetchig_data))
        }else{
            progress.hide()
            showNotificationText(getString(R.string.no_internet_hint))
        }



    }


    private fun observeCovidStatsResponse(){
        mainViewModel.covideResponse.observe(this) {
            if (it != null) {
                when (it) {
                    is NetworkResult.Success -> {
                        showNotificationText()
                        progress.hide()
                        it.data?.let { states ->
                            sortConfirmedCases(states)
                        }
                    }
                    is NetworkResult.Error -> {
                        progress.hide()
                        showNotificationText(it.message.toString())
                        showToast(it.message.toString())
                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun sortConfirmedCases(covidStats: HashMap<String, DistrictData>) {
        mCovidStatsMap.clear()
        mCovidStatsMap.putAll(covidStats)
        covidStats.mapKeys {
            val stateDetail = StateDetails(
                stateName = it.key,
                confirmed = it.value.districtData.values.sumOf { it.confirmed },
                deceased = it.value.districtData.values.sumOf { it.deceased },
                active = it.value.districtData.values.sumOf { it.active },
                recovered = it.value.districtData.values.sumOf { it.recovered }
            )
            stateDetailsList.add(stateDetail)

        }
        Collections.sort(stateDetailsList, SortByConfirmedCaseComparator())
        adapter!!.notifyDataSetChanged()
    }


    private fun setUpCovidStatsRecyclerview() {
        val expandableListView = _binding.expandableListView
        adapter = CustomExpandableListAdapter(this, stateDetailsList, mCovidStatsMap)
        expandableListView.setAdapter(adapter)

        expandableListView.setOnGroupExpandListener {

        }

        expandableListView.setOnGroupCollapseListener {

        }

        expandableListView.setOnChildClickListener { _, v, groupPosition, childPosition, id ->
            DistrictWiseActivity.start(
                this,
                mCovidStatsMap[stateDetailsList[groupPosition].stateName],
                childPosition
                )
            false
        }

    }

    private fun showNotificationText(message : String? = null){
        _binding.notificationText.setMessage(message)
    }



}