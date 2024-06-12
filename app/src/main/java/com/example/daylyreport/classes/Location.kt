package com.example.daylyreport.classes

//Класс, содержащий информацию о местоположении производства работ
data class Location(
    //Начало производства работ
    val beginning: String,

    //Окончание производства работ
    val ending: String,

    //Примечание
    val commentLocation: String
)
