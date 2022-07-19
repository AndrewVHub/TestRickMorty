package com.example.rickmorty.presentation.ui.multiple

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.rickmorty.databinding.CustomItemBinding
import com.example.rickmorty.databinding.LocationItemBinding

sealed class MultipleRecyclerViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root){

    class LocationViewHolder(private val binding: LocationItemBinding): MultipleRecyclerViewHolder(binding) {
        fun bind(item: Location) = with(binding){
            tvName.text = item.locationModel.name
            tvTypeEdit.text = item.locationModel.type
            tvDimensionEdit.text = item.locationModel.dimension
        }
    }

    class CustomViewHolder(private val binding: CustomItemBinding): MultipleRecyclerViewHolder(binding){
        fun bind(item: NoLocation) = with(binding){
            tvCustomData.text = item.string
        }
    }
}