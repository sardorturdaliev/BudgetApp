package com.sardordev.budgetapp.data.db

import android.content.Context
import android.provider.DocumentsContract.Root
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sardordev.budgetapp.data.dao.BudgetDao
import com.sardordev.budgetapp.data.dao.ExpenceDao
import com.sardordev.budgetapp.data.model.BudgetEntity
import com.sardordev.budgetapp.data.model.ExpenceEntity

@Database(entities = [BudgetEntity::class, ExpenceEntity::class], version = 1)
abstract class MyDatabase() : RoomDatabase() {
    abstract fun getBudggetDao(): BudgetDao
    abstract fun getExpenceDao(): ExpenceDao


    companion object {
        private var instance: MyDatabase? = null

        fun init(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, MyDatabase::class.java, "mydbb")
                    .allowMainThreadQueries().build()
            }
        }

        fun getintance(): MyDatabase = instance!!
    }


}