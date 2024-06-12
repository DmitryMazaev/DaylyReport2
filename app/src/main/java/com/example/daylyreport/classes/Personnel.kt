package com.example.daylyreport.classes

//Класс Персонал
data class Personnel (
    //Должность работника, из списка техники, таблица в БД
    val personnelType: String,
    //Время работы
    val timeOfWorkPersonnel: String
)