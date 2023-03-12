package com.sardordev.budgetapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expencetable")
data class ExpenceEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "expencecol")
    val expenceName: String,
    @ColumnInfo(name = "expenceamountcol")
    val expenceAmount: Float


)