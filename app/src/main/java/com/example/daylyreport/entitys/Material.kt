package com.example.daylyreport.entitys

//Класс Материал
data class Material (
    //Наименование материала
    val nameOfMaterial: String,
    //Единица измерения материала
    val unitOfMeasurementOfMaterial: String,
    //Количество материала
    val quantityOfMaterial: Double
)