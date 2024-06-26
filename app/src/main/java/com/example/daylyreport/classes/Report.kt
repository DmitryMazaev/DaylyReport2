package com.example.daylyreport.classes

data class Report (
    //Наименование строительного объекта, из списка строительных объектов (таблица в БД)
    val constructionObject: String = "",
    //Дата начала производства работ (вспомнить установку даты и времени в Android, было занятие)
    val dateOfWork: String = "",
    //Время начала производства работ (вспомнить установку даты и времени в Android, было занятие)
    val timeOfWork: String = "",
    //Поле с видом работ, содержит информацию о количестве и затратах при ее выполнении
    val typeOfWorkList: List<TypeOfWork>? = null,
    //Поле с производителем работ
    val foreman: Foreman? = null,
    //Является ли сменный отчет откорректированным (false - нет, true - да)
    val isAmendedReport: Boolean? = null
)