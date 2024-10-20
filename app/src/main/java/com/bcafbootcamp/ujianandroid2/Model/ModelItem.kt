package com.bcafbootcamp.ujianandroid2.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ModelItem(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama : String,
    val outstanding : String,
    val alamat : String
)
