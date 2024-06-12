package com.example.daylyreport.classes

//Класс Материал
data class Material (
    //Наименование материала, из списка материалов, таблица в БД
    val nameOfMaterial: String,
    //Единица измерения материала, привязана к материалу
    val unitOfMeasurementOfWork: String,
    //Количество материала
    val quantityOfMaterial: Double
)