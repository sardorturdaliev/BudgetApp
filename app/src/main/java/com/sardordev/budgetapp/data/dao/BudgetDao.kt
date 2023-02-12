package com.sardordev.budgetapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.Query
import com.sardordev.budgetapp.data.model.AmountData
import com.sardordev.budgetapp.data.model.BudgetEntity

@Dao
interface BudgetDao {
    @Insert()
    fun insertData(budgetEntity: BudgetEntity)


    @Query("select * from budgettable")
    fun getAllData(): LiveData<List<BudgetEntity>>


    @Query("select amountbudget from budgettable")
    fun getAmounBudget(): LiveData<List<AmountData>>


}