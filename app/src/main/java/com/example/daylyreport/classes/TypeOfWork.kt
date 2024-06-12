package com.example.daylyreport.classes
//Класс вида работ с затратами и их количеством
data class TypeOfWork (
    //Вид работы
    val typicalWork: TypicalWork,
    //Список материалов
    val material: List<Material>,
    //Список транспортных средств
    val transportVehicle: List<TransportVehicle>,
    //Список работников
    val personnel: List<Personnel>
)