package com.example.daylyreport.entitys

//Класс Типовая работа
data class TypicalWork (
    //Вид работ, из списка видов работ, таблица в БД
    val typeOfWork: String,
    //Единица измерения работы, привязана к виду работ
    val unitOfMeasurementOfWork: String,
    //Количество работы
    val quantityOfWork: Double,
)