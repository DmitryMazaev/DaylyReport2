package com.example.daylyreport.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Класс Транспортное средство
@Parcelize
data class TransportVehicle (
    //Номер транспортного средства
    val transportNumber: String,
    //Владелец транспортного средства
    val ownerOfTransport: String,
    //Время работы
    val timeOfWorkTransport: String
): Parcelable