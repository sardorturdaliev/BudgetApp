package com.sardordev.budgetapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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


    @Query("update budgettable set  categoryname = :name , amountbudget = :amount  where id  = :id ")
    fun update(name:String,amount:String,id:Int)


}