package com.example.sharedpreferencespractice

import android.content.Context

class Preferences(val context: Context) {

    val SHARED_NAME = "username"

    val SHARED_PREFERENCES_NAME = "MyDataBase"

    val SHARED_VIP = "vip"

    val storage = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0)

    fun saveName(name:String){
        storage.edit().putString(SHARED_NAME, name).apply()
    }

    fun getName():String{
        return storage.getString(SHARED_NAME, "")!!
    }

    fun saveVip(vip:Boolean){
        storage.edit().putBoolean(SHARED_VIP, vip).apply()
    }

    fun getVip():Boolean{
       return storage.getBoolean(SHARED_VIP,false)
    }

    fun deleteAll(){
        storage.edit().clear().apply()
    }
}