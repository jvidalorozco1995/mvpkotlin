package com.periferiait.contactbusiness.checkkotlinlibraries.model

/**
 * Created by admin on 11/09/17.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * example of work of Kotlin project with Java class.
 */
class NewsResponse {

    @SerializedName("Pagination")
    @Expose
    var pagination: Pagination? = null
    @SerializedName("NewsItem")
    @Expose
    var newsItem: List<NewsItem> = ArrayList()

}