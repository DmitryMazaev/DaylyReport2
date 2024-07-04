package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Класс Типовая работа
@Parcelize
data class TypicalWork (
    //Вид работ, из списка видов работ
    val typeOfWork: String = "",
    //Количество работы
    val quantityOfWork: Double = 0.0
): Parcelable