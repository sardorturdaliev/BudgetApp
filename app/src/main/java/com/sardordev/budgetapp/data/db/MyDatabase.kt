package com.sardordev.budgetapp.data.db

import android.content.Context
import android.provider.DocumentsContract.Root
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sardordev.budgetapp.data.dao.BudgetDao
import com.sardordev.budgetapp.data.model.BudgetEntity

@Database(entities = [BudgetEntity::class], version = 1)
abstract class MyDatabase() : RoomDatabase() {
    abstract fun getBudggetDao(): BudgetDao


    companion object {
        private var instance: MyDatabase? = null

        fun init(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, MyDatabase::class.java, "mydb")
                    .allowMainThreadQueries().build()
            }
        }

        fun getintance(): MyDatabase = instance!!
    }


}