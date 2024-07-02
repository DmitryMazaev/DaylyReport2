package com.example.daylyreport.classes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daylyreport.adapter.ElementReportAdapter
import com.example.daylyreport.databinding.FragmentListReportBinding
import com.example.daylyreport.databinding.FragmentReportBinding
import com.example.daylyreport.entitys.Report
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListReportViewModel: ViewModel() {

    private lateinit var reportList: ArrayList<Report>
    private val firebase = FirebaseDatabase.getInstance().getReference("reportList")

    init {
        Log.d("QQQ init", firebase.toString())
    }
    fun fetchData(binding: FragmentListReportBinding?) {
        Log.d("QQQ", firebase.toString())
        firebase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                reportList = arrayListOf()
                reportList.clear()
                Log.d("QQQ snapshot", snapshot.toString())
                if (snapshot.exists()) {
                    for (reportOne in snapshot.children) {
                        val report = reportOne.getValue(Report::class.java)
                        Log.d("QQQ", report.toString())
                        reportList.add(report!!)
                    }
                }
                Log.d("QQQ list", reportList.toString())
                val elementReportAdapter = ElementReportAdapter (reportList)
                binding?.reportListRecyclerView?.adapter = elementReportAdapter
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}