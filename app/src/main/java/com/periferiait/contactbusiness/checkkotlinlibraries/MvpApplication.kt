package com.periferiait.contactbusiness.checkkotlinlibraries

import android.app.Application
import com.periferiait.contactbusiness.checkkotlinlibraries.DI.NetworkModule
import com.periferiait.contactbusiness.checkkotlinlibraries.view.activities.MainActivity
import dagger.Component

import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

/**
 * Created by admin on 11/09/17.
 */
class MvpApplication : Application() {

    var injector: AppInjector? = null

    @Singleton
    @Component(modules = arrayOf(NetworkModule::class))
    interface AppInjector {

        fun inject(activity: MainActivity)

    }

    override fun onCreate() {
        super.onCreate()
        injector = DaggerMvpApplication_AppInjector.builder().build()

        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }
}