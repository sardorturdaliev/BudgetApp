package com.sardordev.budgetapp.app

import android.app.Application
import com.sardordev.budgetapp.data.db.MyDatabase

class App  : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDatabase.init(this)
    }
}