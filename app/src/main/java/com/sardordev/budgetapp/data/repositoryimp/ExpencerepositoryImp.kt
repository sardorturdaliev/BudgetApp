package com.sardordev.budgetapp.data.repositoryimp

import androidx.lifecycle.LiveData
import com.sardordev.budgetapp.data.dao.ExpenceDao
import com.sardordev.budgetapp.data.model.AmountData
import com.sardordev.budgetapp.data.model.AmountDataExpence
import com.sardordev.budgetapp.data.model.ExpenceEntity
import com.sardordev.budgetapp.domain.repository.ExpenceRepository

class ExpencerepositoryImp(private val expenceDao: ExpenceDao) : ExpenceRepository {


    override fun insert(expenceEntity: ExpenceEntity) {
        expenceDao.insert(expenceEntity)
    }

    override fun getData(): LiveData<List<ExpenceEntity>> {
        return expenceDao.getData()
    }

    override fun getExpenceAmount(): LiveData<List<AmountDataExpence>> {
        return expenceDao.getExpenceAmount()
    }


}