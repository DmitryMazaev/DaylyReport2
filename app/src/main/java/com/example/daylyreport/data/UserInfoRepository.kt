package com.example.daylyreport.data

import com.example.daylyreport.entitys.Foreman

object UserInfoRepository {
    
    private lateinit var currentUser: Foreman
    
    fun setUser(foreman: Foreman) {
        currentUser = foreman
    }
    
    fun getUser() = currentUser
}