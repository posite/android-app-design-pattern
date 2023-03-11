package com.dragonguard.chart

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStore(private val dataStore: DataStore<Preferences>
) {
    companion object {
        val USER_AGE_KEY = stringPreferencesKey("id")
        val USER_FIRST_NAME_KEY = stringPreferencesKey("password")
        val LOGIN_CHECK = booleanPreferencesKey("login_check")
    }

    suspend fun storeUser(
        age: String,
        frontName: String,

    ) {
        dataStore.edit {
            it[USER_AGE_KEY] = age
            it[USER_FIRST_NAME_KEY] = frontName
        }
    }


    val userInt1Flow: Flow<String?> = dataStore.data.map {
        it[USER_AGE_KEY]
    }

    val userInt2Flow: Flow<String?> = dataStore.data.map {
        it[USER_FIRST_NAME_KEY]
    }

    val userLoginCheck: Flow<Boolean?> = dataStore.data.map{
        it[LOGIN_CHECK]
    }



}