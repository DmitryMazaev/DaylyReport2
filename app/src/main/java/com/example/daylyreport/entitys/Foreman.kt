package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Производитель работ
@Parcelize
data class Foreman (
    //Имя производителя работ
    val name: String? = "",
    //Логин для авторизации производителя работ
    val login: String? = "",
    //Пароль для авторизации производителя работ
    val password: String? = ""
): Parcelable