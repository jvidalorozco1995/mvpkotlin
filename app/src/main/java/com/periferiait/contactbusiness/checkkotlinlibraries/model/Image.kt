package com.periferiait.contactbusiness.checkkotlinlibraries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by admin on 11/09/17.
 */

open class Image : RealmObject(){

    @SerializedName("Photo")
    @Expose
    var photo : String? = null

    @SerializedName("Thumb")
    @Expose
    var Thumb : String? = null

    @SerializedName("PhotoCaption")
    @Expose
    var photocaption : String? = null


}
