package com.basim.kotlinappwear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.widget.WearableLinearLayoutManager
import com.basim.kotlinapp.ui.GalleryViewModel
import com.basim.kotlinapp.ui.gallery.GalleryListAdapter
import com.basim.kotlinappwear.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() , AmbientModeSupport.AmbientCallbackProvider {

    private lateinit var ambientController: AmbientModeSupport.AmbientController
    private lateinit var galleryListViewModel: GalleryViewModel
    private var errorSnackbar: Snackbar? = null
    private lateinit var binding : ActivityMainBinding
    private var layoutManager: WearableLinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ambientController = AmbientModeSupport.attach(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        galleryListViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        layoutManager = WearableLinearLayoutManager(this)
        binding.galleryList.layoutManager = layoutManager
        binding.viewModel = galleryListViewModel
        val galleryListAdapter = GalleryListAdapter()
        galleryListViewModel.galleryList.observe(this, Observer{
            if (it != null) {
                galleryListAdapter.updateGalleryList(it.data)
                binding.galleryList.adapter = galleryListAdapter
            }
        })
        galleryListViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
    }

    private fun showError(@StringRes errorMessage:Int){
        val errorClickListener = View.OnClickListener { hideError() }
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.dismiss, errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = AmbientCallback()

    private class AmbientCallback : AmbientModeSupport.AmbientCallback() {

        override fun onEnterAmbient(ambientDetails: Bundle?) {

        }

        override fun onExitAmbient() {

        }

        override fun onUpdateAmbient() {

        }
    }
}
