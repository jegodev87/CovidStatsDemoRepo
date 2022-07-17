package com.jg2022.demoproject.utils.comparators

import com.jg2022.demoproject.model.StateDetails
import java.util.Comparator

class SortByConfirmedCaseComparator : Comparator<StateDetails> {
    override fun compare(stateOne: StateDetails?, stateTwo: StateDetails?): Int {
        if (stateOne == null || stateTwo == null) {
            return 0;
        }
     return  stateTwo.confirmed.compareTo(stateOne.confirmed)
    }
}
