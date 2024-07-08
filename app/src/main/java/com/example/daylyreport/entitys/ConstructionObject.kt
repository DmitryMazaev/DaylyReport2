package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConstructionObject (
    //Наименование объекта строительства
    val name: String = ""
): Parcelable