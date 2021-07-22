package com.example.sharedpreferencespractice

import android.app.Application

class UserVipApplication:Application() {

    companion object{
        lateinit var preferences: Preferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = Preferences(applicationContext)
    }

}