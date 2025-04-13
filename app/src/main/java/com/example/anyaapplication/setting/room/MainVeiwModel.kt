package com.example.anyaapplication.setting.room

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch

class MainViewModel(private val database: MainDb): ViewModel() {

    val itemList = database.dao.getAllItems()

    val nameText = mutableStateOf("")
    val hourInt = mutableIntStateOf(0)
    val minuteInt = mutableIntStateOf(0)
    val categoryBoolean = mutableStateOf(false)

    var nameEntity: NameEntity? = null

    fun insertItem() = viewModelScope.launch {
        val nameItem = nameEntity?.copy(
            name = nameText.value,
            hour = hourInt.intValue,
            minute = minuteInt.intValue,
            category = categoryBoolean.value,
        ) ?: NameEntity(
            name = nameText.value,
            hour = hourInt.intValue,
            minute = minuteInt.intValue,
            category = categoryBoolean.value,
        )
        database.dao.insertItem(nameItem)
        nameEntity = null
        nameText.value = ""
        hourInt.intValue = 0
        minuteInt.intValue = 0
        categoryBoolean.value = false
    }

    fun deleteItem(item: NameEntity) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }

    companion object{
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return MainViewModel(database) as T
            }
        }
    }
}