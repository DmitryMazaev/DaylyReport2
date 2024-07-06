package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConstructionObject (
    val name: String = ""
): Parcelable