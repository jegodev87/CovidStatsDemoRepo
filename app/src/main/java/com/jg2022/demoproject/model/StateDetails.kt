package com.jg2022.demoproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class StateDetails(
    var stateName : String? = null,
    var statecode: String ? = null,
    var active: Int ? = null,
    var confirmed: Int  = 0,
    var migratedother: Int ? = null,
    var deceased: Int ? = null,
    var recovered: Int ? = null,
    var districtData: DistrictData? = null
):Parcelable
@Parcelize
data class DeltaEntity(
    val confirmed: Int,
    val deceased: Int,
    val recovered: Int
):Parcelable

@Parcelize
data class DistrictEntity(
    val active: Int = 0,
    val confirmed: Int = 0,
    val deceased: Int = 0,
    val delta: DeltaEntity? = null,
    val migratedother: Int = 0,
    val notes: String = "",
    val recovered: Int = 0
):Parcelable

@Parcelize
data class DistrictData(
    val districtData: @RawValue HashMap<String,DistrictEntity>
):Parcelable