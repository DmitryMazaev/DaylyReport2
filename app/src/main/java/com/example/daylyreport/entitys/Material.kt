package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Класс Материал
@Parcelize
data class Material (
    //Наименование материала
    val nameOfMaterial: String,
    //Единица измерения материала
    val unitOfMeasurementOfMaterial: String,
    //Количество материала
    val quantityOfMaterial: Double
): Parcelable