package com.sardordev.budgetapp.data.repositoryimp

import androidx.lifecycle.LiveData
import com.sardordev.budgetapp.data.dao.BudgetDao
import com.sardordev.budgetapp.data.model.AmountData
import com.sardordev.budgetapp.data.model.BudgetEntity
import com.sardordev.budgetapp.domain.repository.BudgetRepository

class BudgetrepositoryImp(private val dao: BudgetDao) : BudgetRepository {
    override fun getBudgets(): LiveData<List<BudgetEntity>> {
        return dao.getAllData()
    }

    override fun insert(budgetEntity: BudgetEntity) {
        dao.insertData(budgetEntity)
    }

    override fun getAmountBudget(): LiveData<List<AmountData>> {
        return dao.getAmounBudget()
    }

}