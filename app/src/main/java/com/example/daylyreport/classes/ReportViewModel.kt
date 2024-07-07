package com.example.daylyreport.classes

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daylyreport.databinding.FragmentReportBinding
import com.example.daylyreport.entitys.ConstructionObject
import com.example.daylyreport.entitys.Foreman
import com.example.daylyreport.entitys.Material
import com.example.daylyreport.entitys.Report
import com.example.daylyreport.entitys.TypicalWork
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

class ReportViewModel: ViewModel() {

    private val _constructionObjectFlow = MutableStateFlow<List<ConstructionObject>>(emptyList())
    val constructionObjectFlow = _constructionObjectFlow.asStateFlow()
    private val _typicalWorkFlow = MutableStateFlow<List<TypicalWork>>(emptyList())
    val typicalWorkFlow = _typicalWorkFlow.asStateFlow()
    private val _typicalMaterialFlow = MutableStateFlow<List<Material>>(emptyList())
    val typicalMaterialFlow = _typicalMaterialFlow.asStateFlow()

    private val firebaseConstructionObject = FirebaseDatabase.getInstance().getReference("constructionObject")
    private val firebaseTypicalWork = FirebaseDatabase.getInstance().getReference("typicalWork")
    private val firebaseMaterial = FirebaseDatabase.getInstance().getReference("material")

    init {
        firebaseConstructionObject.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val constructionObjectList = mutableListOf<ConstructionObject>()
                if (snapshot.exists()) {
                    for (constructionObjectOne in snapshot.children) {
                        val constructionObject = constructionObjectOne.getValue(ConstructionObject::class.java)
                        constructionObjectList.add(constructionObject!!)

                    }
                }
                _constructionObjectFlow.value = constructionObjectList
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        firebaseTypicalWork.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val typicalWorkList = mutableListOf<TypicalWork>()
                if (snapshot.exists()) {
                    for (typicalWorkOne in snapshot.children) {
                        val typicalWork = typicalWorkOne.getValue(TypicalWork::class.java)
                        typicalWorkList.add(typicalWork!!)

                    }
                }
                _typicalWorkFlow.value = typicalWorkList
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        firebaseMaterial.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val typicalMaterialList = mutableListOf<Material>()
                if (snapshot.exists()) {
                    for (typicalMaterialOne in snapshot.children) {
                        val typicalMaterial = typicalMaterialOne.getValue(Material::class.java)
                        typicalMaterialList.add(typicalMaterial!!)

                    }
                }
                _typicalMaterialFlow.value = typicalMaterialList
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}


