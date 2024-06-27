package com.example.daylyreport.entitys

//Класс Персонал
data class Personnel (
    //Должность работника, из списка техники, таблица в БД
    val personnelType: String,
    //Время работы
    val timeOfWorkPersonnel: String
)