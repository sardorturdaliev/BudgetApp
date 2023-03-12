package com.sardordev.budgetapp.data.model

import androidx.room.ColumnInfo


data class AmountDataExpence(
    @ColumnInfo(name = "expenceamountcol")
    val expenceAmount: Float
)