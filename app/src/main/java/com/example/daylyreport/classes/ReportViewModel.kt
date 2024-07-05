package com.example.daylyreport.classes

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daylyreport.databinding.FragmentReportBinding
import com.example.daylyreport.entitys.Report
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

class ReportViewModel: ViewModel() {
    
    private val firebase = FirebaseDatabase.getInstance().getReference("reportList")
    private val calendar = Calendar.getInstance()
  
    fun enterDate(binding: FragmentReportBinding?, parentFragmentManager: FragmentManager) {
    
    }
}


