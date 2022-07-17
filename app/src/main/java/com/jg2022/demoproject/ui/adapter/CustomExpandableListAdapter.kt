package com.jg2022.demoproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.jg2022.demoproject.R
import com.jg2022.demoproject.databinding.CovidStatsDistrictWiseRowBinding
import com.jg2022.demoproject.databinding.CovidStatsStateRowBinding
import com.jg2022.demoproject.model.DistrictData
import com.jg2022.demoproject.model.DistrictEntity
import com.jg2022.demoproject.model.StateDetails
import com.jg2022.demoproject.utils.hide
import com.jg2022.demoproject.utils.setColor
import com.jg2022.demoproject.utils.show


class CustomExpandableListAdapter internal constructor(
    private val context: Context,
    private val stateDetailsList: ArrayList<StateDetails>,
    private val dataList: HashMap<String, DistrictData>
) : BaseExpandableListAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var stateViewBinding: CovidStatsStateRowBinding
    private lateinit var districtViewBinding: CovidStatsDistrictWiseRowBinding


    private fun getStateWiseCovidStats(position: Int): StateDetails {
        return stateDetailsList[position]
    }

    private fun getDistrictName(position: Int, expandedDistrictPosition: Int): String {
        return dataList[this.stateDetailsList[position].stateName]!!.districtData.keys.toList()[expandedDistrictPosition]
    }

     fun getDistrictDetails(position: Int, districtKey: String): DistrictEntity? {
        return dataList[this.stateDetailsList[position].stateName]!!.districtData.get(districtKey)
    }

    override fun getChild(listPosition: Int, expandedDistrictPosition: Int): Any {
        return getDistrictName(listPosition, expandedDistrictPosition)

    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        view: View?,
        parent: ViewGroup
    ): View {
        var convertView = view
        val holder: DisctrictDataViewHolder
        if (convertView == null) {
            districtViewBinding = CovidStatsDistrictWiseRowBinding.inflate(inflater)
            convertView = districtViewBinding.root
            holder = DisctrictDataViewHolder()
            holder.distrctNameLabel = districtViewBinding.districtTitle

            convertView.tag = holder
        } else {
            holder = convertView.tag as DisctrictDataViewHolder
        }
        val districtName = getDistrictName(listPosition, expandedListPosition)
        holder.distrctNameLabel!!.text = districtName

        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return this.dataList[this.stateDetailsList[listPosition].stateName]!!.districtData.keys.size
    }

    override fun getGroup(listPosition: Int): Any {
        return this.stateDetailsList[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.stateDetailsList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        view: View?,
        parent: ViewGroup
    ): View {
        var convertView = view
        val holder: StatesDataViewHolder
        if (convertView == null) {
            stateViewBinding = CovidStatsStateRowBinding.inflate(inflater)
            convertView = stateViewBinding.root
            holder = StatesDataViewHolder()
            holder.label = stateViewBinding.stateTitle
            holder.activeCasesLabel = stateViewBinding.activeCases
            holder.confirmedCasesLabel = stateViewBinding.confirmedCases
            holder.recoveredCasesLabel = stateViewBinding.recoveredCases
            holder.deceasedCaseLabel = stateViewBinding.deceasedCases
            convertView.tag = holder
        } else {
            holder = convertView.tag as StatesDataViewHolder
        }
        val stateDetail = getGroup(listPosition) as StateDetails
        holder.apply {
            if (isExpanded) {
                activeCasesLabel?.show()
                confirmedCasesLabel?.show()
                recoveredCasesLabel?.show()
                deceasedCaseLabel?.show()
            } else {
                activeCasesLabel?.hide()
                confirmedCasesLabel?.hide()
                recoveredCasesLabel?.hide()
                deceasedCaseLabel?.hide()
            }
            label?.text = stateDetail.stateName
            activeCasesLabel?.text = "Active \n" + stateDetail.active
            activeCasesLabel?.setColor(R.color.green)
            confirmedCasesLabel?.text = "Confirmed \n" + stateDetail.confirmed
            confirmedCasesLabel?.setColor(R.color.yellow)
            recoveredCasesLabel?.text = "Recovered \n" + stateDetail.recovered
            recoveredCasesLabel?.setColor(R.color.blue)
            deceasedCaseLabel?.text = "Deceased \n" + stateDetail.deceased
            deceasedCaseLabel?.setColor(R.color.red)
        }

        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

    inner class DisctrictDataViewHolder {
        internal var distrctNameLabel: TextView? = null

    }

    inner class StatesDataViewHolder {
        internal var label: TextView? = null
        internal var confirmedCasesLabel: TextView? = null
        internal var activeCasesLabel: TextView? = null
        internal var recoveredCasesLabel: TextView? = null
        internal var deceasedCaseLabel: TextView? = null
    }
}
