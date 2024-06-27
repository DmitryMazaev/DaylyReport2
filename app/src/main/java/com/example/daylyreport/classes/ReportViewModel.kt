package com.example.daylyreport.classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daylyreport.ReportFragment
import com.example.daylyreport.databinding.FragmentReportBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.values
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

class ReportViewModel: ViewModel() {
    init {

    }
    private val firebase = FirebaseDatabase.getInstance().getReference("reportList")
    private var binding: FragmentReportBinding? = null
    fun addNewReport(report: Report) {
        viewModelScope.launch(Dispatchers.IO) {
            val reportId = firebase.push().key!!
            firebase.child(reportId).setValue(report)
        }
    }

    fun addNewReportAlt(binding: FragmentReportBinding?) {
        viewModelScope.launch(Dispatchers.IO) {
            val reportId = firebase.push().key!!.toString()
            //binding?.reportIdEditText?.text = reportId
            val report = Report(
                reportId,
                binding?.constructionObjectEditTextForEnter?.text.toString(),
                binding?.dateFromDateAndTime?.text.toString(),
                "08:00",
                null,
                null,
                null)
            firebase.child(reportId).setValue(report)
        }
    }
}