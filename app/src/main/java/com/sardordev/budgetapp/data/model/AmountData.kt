package com.sardordev.budgetapp.data.model

import androidx.room.ColumnInfo


data class AmountData(
    @ColumnInfo(name = "amountbudget")
    val amountBudgets: Float
)