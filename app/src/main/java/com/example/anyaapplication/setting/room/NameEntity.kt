package com.example.anyaapplication.setting.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NameEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "hour")
    val hour: String,
    @ColumnInfo(name = "minute")
    val minute: String,
    @ColumnInfo(name = "category")
    var category: Boolean
)