package com.basim.kotlinapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Class which provides a model for Image
 * @constructor Sets all properties of the Image
 * @property id id of the Image
 * @property title title of the Image
 * @property description description of the Image
 * @property type type of the Image
 * @property link URL of the Image
 */
@Parcelize
data class Image(
    var id : String?,
    val title : String?,
    val description : String?,
    val type : String?,
    val link : String?) : Parcelable