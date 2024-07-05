package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Класс Персонал
@Parcelize
data class Personnel (
    //Должность работника
    val personnelType: String = "",
    //Время работы
    val timeOfWorkPersonnel: Double = 0.0
): Parcelable