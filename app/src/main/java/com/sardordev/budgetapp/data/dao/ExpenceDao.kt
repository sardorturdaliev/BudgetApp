package com.sardordev.budgetapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sardordev.budgetapp.data.model.AmountData
import com.sardordev.budgetapp.data.model.AmountDataExpence
import com.sardordev.budgetapp.data.model.ExpenceEntity

@Dao
interface ExpenceDao {

    @Insert()
    fun insert(expenceEntity: ExpenceEntity)

    @Query("select * from expencetable")
    fun getData(): LiveData<List<ExpenceEntity>>


    @Query("select expenceamountcol from expencetable ")
    fun getExpenceAmount(): LiveData<List<AmountDataExpence>>

}