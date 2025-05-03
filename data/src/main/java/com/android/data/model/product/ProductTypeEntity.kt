package com.android.data.model.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MenuType")
data class ProductTypeEntity (
    @PrimaryKey val id: Int,
    val name: String
)