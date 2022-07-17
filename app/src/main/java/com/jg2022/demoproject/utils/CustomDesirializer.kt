package com.jg2022.demoproject.utils

/*
class CustomDesirializer(val gson: Gson) : JsonDeserializer<ArrayList<StateDetails>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ArrayList<StateDetails> {

        val sortedStatesList = ArrayList<StateDetails>()
        val state = StateDetails()

        */
/**
         * measuring time taken for the process
         *//*

        val time = measureTimeMillis {
            // call your function here
            json.asJsonObject.entrySet().map {
                val result = gson.fromJson(it.value.asJsonObject, DistrictData::class.java)
                state.statecode = it.value.asJsonObject.get("statecode").asString
                state.stateName = it.key
                state.districtData = result
                state.confirmed = result.districtData.values.sumOf { it.confirmed }
                state.active = result.districtData.values.sumOf { it.active }
                state.deceased = result.districtData.values.sumOf { it.deceased }
                state.recovered = result.districtData.values.sumOf { it.recovered }
                sortedStatesList.add(state)
            }
        }

        writeLogg("time taken to print the result  -->> $state")



        return sortedStatesList
    }
}*/
