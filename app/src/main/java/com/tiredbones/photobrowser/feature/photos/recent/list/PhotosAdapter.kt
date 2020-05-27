package com.tiredbones.photobrowser.feature.photos.recent.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.tiredbones.photobrowser.R

class PhotosAdapter : PagedListAdapter<PhotoItem, PhotosViewHolder>(PhotosAsyncDiffUtil()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder =
      PhotosViewHolder(
          DataBindingUtil.inflate(
              LayoutInflater.from(parent.context),
              R.layout.item_photo,
              parent,
              false
          )
      )

  override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}
