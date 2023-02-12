package com.sardordev.budgetapp.screens.budgetscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sardordev.budgetapp.data.dao.BudgetDao
import com.sardordev.budgetapp.data.db.MyDatabase
import com.sardordev.budgetapp.data.model.AmountData
import com.sardordev.budgetapp.data.model.BudgetEntity
import com.sardordev.budgetapp.data.repositoryimp.BudgetrepositoryImp

class BudgetViewModel() : ViewModel() {
    val budgetDao: BudgetDao = MyDatabase.getintance().getBudggetDao()
    val budgetrepositoryImp = BudgetrepositoryImp(budgetDao)
    val getData: LiveData<List<BudgetEntity>> = budgetrepositoryImp.getBudgets()
    val getAmountBudget: LiveData<List<AmountData>> = budgetrepositoryImp.getAmountBudget()


    fun insert(budgetEntity: BudgetEntity) {
        budgetrepositoryImp.insert(budgetEntity)
    }




}