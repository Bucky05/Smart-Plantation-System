package com.example.smartagriculturesystem

interface UIUpdaterInterface {


    fun resetUIWithConnection(status: Boolean)
    fun updateStatusViewWith(status: String)
    fun update(message: String)


}