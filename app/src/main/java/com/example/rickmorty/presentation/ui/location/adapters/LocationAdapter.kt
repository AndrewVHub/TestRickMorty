package com.example.rickmorty.presentation.ui.location.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.data.models.location.Location
import com.example.rickmorty.databinding.LocationItemBinding

class LocationAdapter: RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    var collection: List<Location>? = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class LocationViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = LocationItemBinding.bind(view)
        fun bind(item: Location) = with(binding){
            tvName.text = item.name
            tvTypeEdit.text = item.type
            tvDimensionEdit.text = item.dimension
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        collection.let{
            val item = it?.get(position)
            if (item != null) {
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int = collection?.size?: 0

}