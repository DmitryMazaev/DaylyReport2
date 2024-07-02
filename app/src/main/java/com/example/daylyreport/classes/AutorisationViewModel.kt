package com.example.daylyreport.classes

import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daylyreport.ReportFragmentArgs
import com.example.daylyreport.adapter.AutorisationAdapter
import com.example.daylyreport.adapter.ElementReportAdapter
import com.example.daylyreport.databinding.FragmentAutorisationBinding
import com.example.daylyreport.databinding.FragmentListReportBinding
import com.example.daylyreport.databinding.FragmentReportBinding
import com.example.daylyreport.entitys.Foreman
import com.example.daylyreport.entitys.Report
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

class AutorisationViewModel: ViewModel() {
    init {

    }
    lateinit var foremansList: ArrayList<Foreman>
    private val firebase = FirebaseDatabase.getInstance().getReference("foreman")
    var listString: ArrayList<String> = ArrayList()

    fun addListOfForemans(binding: FragmentAutorisationBinding?) {
        firebase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //foremansList = arrayListOf()
                foremansList.clear()
                if (snapshot.exists()) {
                    for (foremanOne in snapshot.children) {
                        val foreman = foremanOne.getValue(Foreman::class.java)
                        foremansList.add(foreman!!)

                    }
                }
                //val autorisationAdapter = AutorisationAdapter (foremansList)
                //binding?.foremansRecyclerView?.adapter = autorisationAdapter
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun addToAutoComplite(binding: FragmentAutorisationBinding?) {
        firebase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foremansList = arrayListOf()
                foremansList.clear()
                if (snapshot.exists()) {
                    for (foremanOne in snapshot.children) {
                        val foreman = foremanOne.getValue(Foreman::class.java)
                        foremansList.add(foreman!!)
                        listString.add(foreman.name!!)
                        Log.e("********", foreman.name)
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


}


