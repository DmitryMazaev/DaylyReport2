package com.example.daylyreport.classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daylyreport.databinding.FragmentReportBinding
import com.example.daylyreport.entitys.Report
import com.google.firebase.database.FirebaseDatabase
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

    fun addNewReportAlt(binding: FragmentReportBinding?) {
        viewModelScope.launch(Dispatchers.IO) {
            val reportId = firebase.push().key!!.toString()
            val report = Report(
                reportId,
                binding?.constructionObjectEditTextForEnter?.text.toString(),
                binding?.dateFromDateAndTime?.text.toString(),
                "08:00",
                null,
                null,
                null)
            binding?.reportId?.hint = report.reportId.toString()
            firebase.child(reportId).setValue(report)
        }
    }
}