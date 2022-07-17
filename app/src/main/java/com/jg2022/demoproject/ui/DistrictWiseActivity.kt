package com.jg2022.demoproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jg2022.demoproject.R
import com.jg2022.demoproject.databinding.DistrictWiseActivityBinding
import com.jg2022.demoproject.model.DistrictData
import com.jg2022.demoproject.utils.setColor
import com.jg2022.demoproject.utils.setStatusBarColor
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DistrictWiseActivity : AppCompatActivity() {


    companion object {
        private const val TYPE_DISTRICT_DETAILS = "district_wise_detail"
        private const val TYPE_DISTRICT_POSITION = "district_wise_position"

        @JvmStatic
        fun start(context: Context, districtData: DistrictData?, mPosition: Int) {
            val starter = Intent(context, DistrictWiseActivity::class.java)
            starter.putExtra(TYPE_DISTRICT_DETAILS, districtData)
            starter.putExtra(TYPE_DISTRICT_POSITION, mPosition)
            context.startActivity(starter)
        }
    }

    private lateinit var _binding: DistrictWiseActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DistrictWiseActivityBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setStatusBarColor()

        val districtDetail: DistrictData? = intent.getParcelableExtra(TYPE_DISTRICT_DETAILS)
        val mPosition: Int = intent.getIntExtra(TYPE_DISTRICT_POSITION, 0)
        districtDetail?.let {
            mPosition.let {
                val districtName = districtDetail.districtData.keys.toList()[mPosition]
                setTitle(districtName + " Stats")
                _binding.districtName.text = "District Name $districtName"
                _binding.activeCases.text =
                    "Active cases " + districtDetail.districtData[districtName]?.active.toString()
                _binding.activeCases.setColor(R.color.green)
                _binding.confirmedCases.text =
                    "Confirmed cases " + districtDetail.districtData[districtName]?.confirmed.toString()
                _binding.confirmedCases.setColor(R.color.yellow)
                _binding.recoveredCases.text =
                    "Recovered cases " + districtDetail.districtData[districtName]?.recovered.toString()
                _binding.recoveredCases.setColor(R.color.blue)
                _binding.deceasedCases.text =
                    "Deceased cases " + districtDetail.districtData[districtName]?.deceased.toString()
                _binding.deceasedCases.setColor(R.color.red)

                _binding.deceasedCases.text =
                    "Delta Virus Reported " + districtDetail.districtData[districtName]?.delta?.confirmed.toString()
                _binding.deceasedCases.setColor(R.color.delta)
            }
        }


    }


}