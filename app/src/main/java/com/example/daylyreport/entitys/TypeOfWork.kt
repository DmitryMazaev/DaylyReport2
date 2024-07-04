package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Класс вида работ с затратами и их количеством
@Parcelize
data class TypeOfWork (
    //Вид работы
    val typicalWork: TypicalWork? = null,
    //Поле, содержащее информацию о месте производства работ
    val location: Location? = null,
    //Список материалов
    val materialList: List<Material>? = null,
    //Список транспортных средств
    val transportVehicleList: List<TransportVehicle>? = null,
    //Список работников
    val personnelList: List<Personnel>? = null
): Parcelable