package com.tiredbones.photobrowser.feature.photos.recent.list

import androidx.recyclerview.widget.RecyclerView
import com.tiredbones.photobrowser.databinding.ItemPhotoBinding

class PhotosViewHolder(
    private val binding: ItemPhotoBinding
) : RecyclerView.ViewHolder(binding.root) {

  fun bind(item: PhotoItem?) {
    binding.item = item
  }
}
