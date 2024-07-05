package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//Класс Материал
@Parcelize
data class Material (
    //Наименование материала
    val nameOfMaterial: String = "",
    //Единица измерения материала
//    val unitOfMeasurementOfMaterial: String,
    //Количество материала
    val quantityOfMaterial: Double = 0.0
): Parcelable