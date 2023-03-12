package com.sardordev.budgetapp.domain.repository

import androidx.lifecycle.LiveData
import com.sardordev.budgetapp.data.model.AmountData
import com.sardordev.budgetapp.data.model.BudgetEntity
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {

    fun getBudgets(): LiveData<List<BudgetEntity>>

    fun insert(budgetEntity: BudgetEntity)

    fun getAmountBudget(): LiveData<List<AmountData>>


    fun updata(name:String,amount:String,id:Int)




}