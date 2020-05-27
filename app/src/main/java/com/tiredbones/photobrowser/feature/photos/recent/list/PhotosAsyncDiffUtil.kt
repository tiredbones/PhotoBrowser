package com.tiredbones.photobrowser.feature.photos.recent.list

import androidx.recyclerview.widget.DiffUtil

class PhotosAsyncDiffUtil : DiffUtil.ItemCallback<PhotoItem>() {

  override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean = oldItem.id == newItem.id

  override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean = oldItem == newItem

}
