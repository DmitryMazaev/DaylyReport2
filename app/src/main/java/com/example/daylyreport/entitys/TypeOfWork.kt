package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Класс вида работ с затратами и их количеством
@Parcelize
data class TypeOfWork (
    //Вид работы
    val typicalWork: TypicalWork?,
    //Поле, содержащее информацию о месте производства работ
    val location: Location?,
    //Список материалов
    val materialList: List<Material>?,
    //Список транспортных средств
    val transportVehicleList: List<TransportVehicle>?,
    //Список работников
    val personnelList: List<Personnel>?
): Parcelable