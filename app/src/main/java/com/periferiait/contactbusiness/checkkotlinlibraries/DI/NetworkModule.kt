package com.periferiait.contactbusiness.checkkotlinlibraries.DI

import android.provider.SyncStateContract
import com.periferiait.contactbusiness.checkkotlinlibraries.api.NewsApi
import com.periferiait.contactbusiness.checkkotlinlibraries.presenter.NewsPresenter
import com.periferiait.contactbusiness.checkkotlinlibraries.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by admin on 11/09/17.
 */

@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun provideNewsPresenter(): NewsPresenter {
        return NewsPresenter()
    }

    @Provides
    @Singleton
    internal fun provideNewApiInterface(): NewsApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.NEWS_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        return retrofit.create(NewsApi::class.java)
    }

}