package com.example.daylyreport.entitys

//Класс Материал
data class Material (
    //Наименование материала, из списка материалов, таблица в БД
    val nameOfMaterial: String,
    //Единица измерения материала, привязана к материалу
    val unitOfMeasurementOfMaterial: String,
    //Количество материала
    val quantityOfMaterial: Double
)