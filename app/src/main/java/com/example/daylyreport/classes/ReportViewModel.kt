package com.example.daylyreport.classes
import androidx.lifecycle.ViewModel
import com.example.daylyreport.entitys.ConstructionObject
import com.example.daylyreport.entitys.Material
import com.example.daylyreport.entitys.Personnel
import com.example.daylyreport.entitys.TransportVehicle
import com.example.daylyreport.entitys.TypicalWork
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReportViewModel: ViewModel() {

    private val _constructionObjectFlow = MutableStateFlow<List<ConstructionObject>>(emptyList())
    val constructionObjectFlow = _constructionObjectFlow.asStateFlow()
    private val _typicalWorkFlow = MutableStateFlow<List<TypicalWork>>(emptyList())
    val typicalWorkFlow = _typicalWorkFlow.asStateFlow()
    private val _typicalMaterialFlow = MutableStateFlow<List<Material>>(emptyList())
    val typicalMaterialFlow = _typicalMaterialFlow.asStateFlow()
    private val _typicalTransportFlow = MutableStateFlow<List<TransportVehicle>>(emptyList())
    val typicalTransportFlow = _typicalTransportFlow.asStateFlow()
    private val _typicalPersonnelFlow = MutableStateFlow<List<Personnel>>(emptyList())
    val typicalPersonnelFlow = _typicalPersonnelFlow.asStateFlow()

    private val firebaseConstructionObject = FirebaseDatabase.getInstance().getReference("constructionObject")
    private val firebaseTypicalWork = FirebaseDatabase.getInstance().getReference("typicalWork")
    private val firebaseMaterial = FirebaseDatabase.getInstance().getReference("material")
    private val firebaseTransport = FirebaseDatabase.getInstance().getReference("transport")
    private val firebasePersonnel = FirebaseDatabase.getInstance().getReference("personnel")

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

        firebaseTransport.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val typicalTransportList = mutableListOf<TransportVehicle>()
                if (snapshot.exists()) {
                    for (typicalTransportOne in snapshot.children) {
                        val typicalTransport = typicalTransportOne.getValue(TransportVehicle::class.java)
                        typicalTransportList.add(typicalTransport!!)

                    }
                }
                _typicalTransportFlow.value = typicalTransportList
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        firebasePersonnel.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val typicalPersonnelList = mutableListOf<Personnel>()
                if (snapshot.exists()) {
                    for (typicalPersonnelOne in snapshot.children) {
                        val typicalPersonnel = typicalPersonnelOne.getValue(Personnel::class.java)
                        typicalPersonnelList.add(typicalPersonnel!!)

                    }
                }
                _typicalPersonnelFlow.value = typicalPersonnelList
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}


