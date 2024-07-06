package com.example.daylyreport.entitys

import android.os.Parcelable
import com.example.daylyreport.data.UserInfoRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.parcelize.Parcelize

@Parcelize
data class Report(
    val reportId: String = FirebaseDatabase.getInstance().getReference("reportList").push().key!!.toString(),
    //Наименование строительного объекта
    val constructionObject: ConstructionObject? = null,
    //Дата начала производства работ
    val dateOfWork: String? = "",
    //Время начала производства работ
    val timeOfWork: String? = "",
    //Поле с видом работ, содержит информацию о количестве и затратах при ее выполнении
    val typeOfWorkList: List<TypeOfWork> = emptyList(),
    //Поле с производителем работ
    val foreman: Foreman? = UserInfoRepository.getUser(),
    //Является ли сменный отчет откорректированным (false - нет, true - да)
    val isAmendedReport: Boolean? = null
): Parcelable