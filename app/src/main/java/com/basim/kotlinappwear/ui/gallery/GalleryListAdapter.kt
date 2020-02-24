package com.basim.kotlinapp.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.basim.kotlinapp.data.model.Gallery
import com.basim.kotlinappwear.R
import com.basim.kotlinappwear.databinding.ItemGalleryGridBinding

/**
 * Adapter for Gallery List
 */
class GalleryListAdapter: RecyclerView.Adapter<GalleryListAdapter.ViewHolder>() {
    private lateinit var galleryList: List<Gallery>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemGalleryGridBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_gallery_grid,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(galleryList[position])
    }

    override fun getItemCount(): Int {
        return if (::galleryList.isInitialized) galleryList.size else 0
    }

    fun updateGalleryList(productList: List<Gallery>) {
        this.galleryList = productList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemGalleryGridBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = GalleryItemViewModel()
        fun bind(gallery: Gallery){
            viewModel.bind(gallery)
            binding.viewModel = viewModel
        }
    }
}