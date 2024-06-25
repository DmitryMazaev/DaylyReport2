package com.example.daylyreport.classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.values
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReportViewModel: ViewModel() {
    init {

    }
    private val firebase = FirebaseDatabase.getInstance().getReference("reportList")

    fun addNewReport(report: Report) {
        viewModelScope.launch(Dispatchers.IO) {
            val reportId = firebase.push().key!!
            firebase.child(reportId).setValue(report)
        }
    }

}