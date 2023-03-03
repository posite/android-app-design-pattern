package com.dragonguard.chart

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class dataStore(private val dataStore: DataStore<Preferences>
) {
    companion object {
        val USER_AGE_KEY = intPreferencesKey("int1")
        val USER_FIRST_NAME_KEY = intPreferencesKey("int2")

    }

    suspend fun storeUser(
        age: Int,
        frontName: Int,
    ) {
        dataStore.edit {
            it[USER_AGE_KEY] = age
            it[USER_FIRST_NAME_KEY] = frontName

        }
    }

    val userInt1Flow: Flow<Int?> = dataStore.data.map {
        it[USER_AGE_KEY]
    }

    val userInt2Flow: Flow<Int?> = dataStore.data.map {
        it[USER_FIRST_NAME_KEY]
    }



}