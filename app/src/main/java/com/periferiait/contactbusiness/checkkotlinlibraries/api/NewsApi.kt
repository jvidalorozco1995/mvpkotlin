package com.periferiait.contactbusiness.checkkotlinlibraries.api

import com.periferiait.contactbusiness.checkkotlinlibraries.model.NewsResponse
import retrofit2.http.GET
import rx.Observable

/**
 * Created by admin on 11/09/17.
 */
open interface NewsApi {

    @GET("/feeds/newsdefaultfeeds.cms?feedtype=sjson")
    fun getNews(): Observable<NewsResponse>
}