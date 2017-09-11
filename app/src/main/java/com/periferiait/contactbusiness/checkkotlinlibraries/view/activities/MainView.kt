package com.periferiait.contactbusiness.checkkotlinlibraries.view.activities

import com.periferiait.contactbusiness.checkkotlinlibraries.model.NewsItem

/**
 * Created by admin on 11/09/17.
 */


interface MainView {

    fun onNewsItemLoaded(newsItems: List<NewsItem>)

    fun onError(throwable: Throwable?)

    fun hideLoading()

}