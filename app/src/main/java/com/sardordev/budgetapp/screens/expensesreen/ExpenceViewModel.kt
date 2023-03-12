package com.sardordev.budgetapp.screens.expensesreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sardordev.budgetapp.data.dao.ExpenceDao
import com.sardordev.budgetapp.data.db.MyDatabase
import com.sardordev.budgetapp.data.model.AmountData
import com.sardordev.budgetapp.data.model.AmountDataExpence
import com.sardordev.budgetapp.data.model.ExpenceEntity
import com.sardordev.budgetapp.data.repositoryimp.ExpencerepositoryImp
import com.sardordev.budgetapp.domain.repository.ExpenceRepository

class ExpenceViewModel : ViewModel() {
    private val expenceDao: ExpenceDao = MyDatabase.getintance().getExpenceDao()
    private val expenceRepository = ExpencerepositoryImp(expenceDao)
    val getExpence: LiveData<List<ExpenceEntity>> = expenceRepository.getData()
    val getAmountAllExpence: LiveData<List<AmountDataExpence>> = expenceRepository.getExpenceAmount()

    fun insert(expenceEntity: ExpenceEntity) {
        expenceRepository.insert(expenceEntity)
    }


}