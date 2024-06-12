package com.example.daylyreport.classes

//Класс Трнаспортное средство
data class TransportVehicle (
    //Номер транспортного средства, из списка техники, таблица в БД
    val transportNumber: String,
    //Владелец транспортного средства
    val ownerOfTransport: String,
    //Время работы
    val timeOfWorkTransport: String
)