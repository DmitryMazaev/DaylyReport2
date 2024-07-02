package com.example.daylyreport.entitys

//Класс Типовая работа
data class TypicalWork (
    //Вид работ, из списка видов работ
    val typeOfWork: String,
    //Единица измерения работы
    val unitOfMeasurementOfWork: String,
    //Количество работы
    val quantityOfWork: Double,
)