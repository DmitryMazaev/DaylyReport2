package com.example.daylyreport.models

import androidx.lifecycle.ViewModel
import com.example.daylyreport.entitys.Foreman
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AutorisationViewModel: ViewModel() {
    
    private val _foremanFlow = MutableStateFlow<List<Foreman>>(emptyList())
    val foremanFlow = _foremanFlow.asStateFlow()
    
    private val firebase = FirebaseDatabase.getInstance().getReference("foreman")

    init {
        firebase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val foremanList = mutableListOf<Foreman>()
                if (snapshot.exists()) {
                    for (foremanOne in snapshot.children) {
                        val foreman = foremanOne.getValue(Foreman::class.java)
                        foremanList.add(foreman!!)
                        
                    }
                }
                _foremanFlow.value = foremanList
            }
            
            override fun onCancelled(error: DatabaseError) {
            
            }
        })
    }
    
    fun checkPassword(login: String, pass: String, foremanList: List<Foreman>) : Boolean{
        for (foreman in foremanList) {
            if (foreman.login.equals(login) && foreman.password.equals(pass)) {
                return true
            }
        }
        return false
    }

    fun checkLogin(name: String, foremanList: List<Foreman>) : String{
        for (foreman in foremanList) {
            if (foreman.name.equals(name) ) {
                return foreman.login
            }
        }
        return ""
    }
}
