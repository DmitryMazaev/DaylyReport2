package com.example.daylyreport.entitys

//Производитель работ
data class Foreman (
    //Имя производителя работ
    val name: String? = "",
    //Логин для авторизации производителя работ
    val login: String? = "",
    //Пароль для авторизации производителя работ
    val password: String? = ""
)