package com.example.codechallenge

import AppComponent
import android.app.Application

class CodeChallengeApplication : Application() {

    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerAppComponent()
    }

    private fun initDaggerAppComponent() {
        appComponent  = DaggerAppComponent.factory().create(applicationContext)
    }
}