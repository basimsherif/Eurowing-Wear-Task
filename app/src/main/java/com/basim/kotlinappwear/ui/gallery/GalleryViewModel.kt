package com.basim.kotlinapp.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.basim.kotlinapp.base.BaseViewModel
import com.basim.kotlinapp.data.model.GalleryRoot
import com.basim.kotlinappwear.R
import com.basim.mercari.data.model.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
/**
 * Viewmodel class for Gallery listing page
 */
class GalleryViewModel : BaseViewModel(){
    @Inject
    lateinit var apiInterface: ApiInterface

    private lateinit var subscription: Disposable
    val galleryList: MutableLiveData<GalleryRoot> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val recyclerVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()

    init{
        loadGalleries()
    }

    fun loadGalleries(){
        subscription = apiInterface.getImages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveGalleryStart() }
            .doOnTerminate { onRetrieveGalleryFinish() }
            .subscribe( { result -> onRetrieveGallerySuccess(result)},
                { result ->onRetrieveGalleryError(result)}
            )
    }

    private fun onRetrieveGalleryStart(){
        loadingVisibility.value = View.VISIBLE
        recyclerVisibility.value = View.GONE
    }

    private fun onRetrieveGalleryFinish(){
        loadingVisibility.value = View.GONE
        recyclerVisibility.value = View.VISIBLE
    }

    private fun onRetrieveGallerySuccess(imageList:GalleryRoot){
        this.galleryList.value = imageList
    }

    private fun onRetrieveGalleryError(result: Throwable){
        errorMessage.value = R.string.gallery_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}
