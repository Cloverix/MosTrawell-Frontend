package com.example.mostrawell.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.mostrawell.data.userDataStore
import com.example.mostrawell.domain.util.ProfileDataManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val dataStoreModule = module {
    single<DataStore<Preferences>> { androidContext().userDataStore }
    single<ProfileDataManager> { ProfileDataManager(androidContext()) }
}