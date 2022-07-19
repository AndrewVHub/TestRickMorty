package com.example.rickmorty.presentation.ui.multiple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.data.models.location.LocationModel
import com.example.rickmorty.databinding.CustomItemBinding
import com.example.rickmorty.databinding.LocationItemBinding
import com.example.rickmorty.presentation.ui.location.adapters.LocationAdapter

class MultipleAdapter: RecyclerView.Adapter<MultipleRecyclerViewHolder>() {


    var items = listOf<MultipleRecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleRecyclerViewHolder {
        return when(viewType){
            ItemType.LOCATION.ordinal -> MultipleRecyclerViewHolder.LocationViewHolder(
                LocationItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ItemType.NO_LOCATION.ordinal -> MultipleRecyclerViewHolder.CustomViewHolder(
                CustomItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: MultipleRecyclerViewHolder, position: Int) {
        when(holder){
            is MultipleRecyclerViewHolder.LocationViewHolder -> holder.bind(items[position] as Location)
            is MultipleRecyclerViewHolder.CustomViewHolder -> holder.bind(items[position] as NoLocation)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is Location -> ItemType.LOCATION.ordinal
            is NoLocation -> ItemType.NO_LOCATION.ordinal
        }
    }

}

enum class ItemType{
    LOCATION, NO_LOCATION
}