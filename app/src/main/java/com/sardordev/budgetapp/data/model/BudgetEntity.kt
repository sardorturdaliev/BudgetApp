package com.sardordev.budgetapp.data.model

import androidx.room.*

@Entity(tableName = "budgettable")
data class BudgetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "categoryname")
    val categoryName: String,
    @ColumnInfo(name = "amountbudget")
    val amountBudgets: Int
)
