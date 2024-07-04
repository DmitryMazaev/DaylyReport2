package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Класс, содержащий информацию о местоположении производства работ
@Parcelize
class Location(
    //Начало производства работ
    val beginning: String = "",

    //Окончание производства работ
    val ending: String = "",

    //Примечание
    val commentLocation: String = ""
): Parcelable
