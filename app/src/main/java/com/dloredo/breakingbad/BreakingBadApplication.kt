package com.dloredo.breakingbad

import android.app.Application

class BreakingBadApplication: Application() {

    companion object{
        lateinit var breakingBadAPI: BreakingBadAPI
    }

    override fun onCreate() {
        super.onCreate()
        breakingBadAPI = BreakingBadAPI.getInstance(this)
    }
}