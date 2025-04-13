package com.example.anyaapplication.setting

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(private val context: Context) {

    suspend fun saveSettings(settingData: SettingData) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey("userName")] = settingData.userName
            pref[stringPreferencesKey("userAge")] = settingData.userAge
            pref[stringPreferencesKey("userHeight")] = settingData.userHeight
            pref[stringPreferencesKey("userWeight")] = settingData.userWeight
            pref[stringPreferencesKey("userBloodType")] = settingData.userBloodType
            pref[stringPreferencesKey("userMedicines")] = settingData.userMedicines
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingData(
            pref[stringPreferencesKey("userName")] ?: "",
            pref[stringPreferencesKey("userAge")] ?: "",
            pref[stringPreferencesKey("userHeight")] ?: "",
            pref[stringPreferencesKey("userWeight")] ?: "",
            pref[stringPreferencesKey("userBloodType")] ?: "",
            pref[stringPreferencesKey("userMedicines")] ?: "",
        )
    }
}

data class SettingData(
    val userName: String,
    val userAge: String,
    val userHeight: String,
    val userWeight: String,
    val userBloodType: String,
    val userMedicines: String
)