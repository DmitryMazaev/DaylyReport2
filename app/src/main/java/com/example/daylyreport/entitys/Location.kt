package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Класс, содержащий информацию о местоположении производства работ
@Parcelize
class Location(
    //Начало производства работ, пикет
    val beginningPiket: String = "",

    //Начало производства работ, плюс
    val beginningPlus: String = "",

    //Окончание производства работ, пикет
    val endingPiket: String = "",

    //Окончание производства работ, плюс
    val endingPlus: String = "",

    //Примечание
    val commentLocation: String = ""
): Parcelable
