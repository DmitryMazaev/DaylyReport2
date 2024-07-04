package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Класс Типовая работа
@Parcelize
data class TypicalWork (
    //Вид работ, из списка видов работ
    val typeOfWork: String,
    //Единица измерения работы
    val unitOfMeasurementOfWork: String,
    //Количество работы
    val quantityOfWork: Double,
): Parcelable