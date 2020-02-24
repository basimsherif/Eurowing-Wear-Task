package com.basim.mercari.data.model

import com.basim.kotlinapp.data.model.GalleryRoot
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * An Interface to communicate with Imgur API using Retrofit
 * */
interface ApiInterface {

    /**
     * Get the list of the images from the API
     */
    @GET("gallery/hot")
    fun getImages(): Observable<GalleryRoot>
}