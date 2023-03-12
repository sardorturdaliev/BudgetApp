package com.sardordev.budgetapp.domain.repository

import androidx.lifecycle.LiveData
import com.sardordev.budgetapp.data.model.AmountData
import com.sardordev.budgetapp.data.model.AmountDataExpence
import com.sardordev.budgetapp.data.model.ExpenceEntity

interface ExpenceRepository {

    fun insert(expenceEntity: ExpenceEntity)

    fun getData(): LiveData<List<ExpenceEntity>>

    fun getExpenceAmount(): LiveData<List<AmountDataExpence>>
}