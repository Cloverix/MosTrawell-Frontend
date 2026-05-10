package com.example.mostrawell.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.mostrawell.data.remote.AuthManager
import com.example.mostrawell.data.userDataStore
import com.example.mostrawell.domain.util.AuthState
import com.example.mostrawell.domain.util.ProfileManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { AuthState() }
    single<DataStore<Preferences>> { androidContext().userDataStore }
    single<ProfileManager> { ProfileManager(androidContext()) }
    single<AuthManager> { AuthManager(get()) }
}