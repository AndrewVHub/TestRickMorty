package com.example.rickmorty.presentation.ui.episode.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.Episode
import com.example.rickmorty.databinding.CharacterItemBinding
import com.example.rickmorty.databinding.EpisodeItemBinding
import com.example.rickmorty.presentation.ui.characters.adapters.CharacterAdapter
import com.example.rickmorty.presentation.utils.load

class EpisodeAdapter: RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    var collection: List<Episode>? = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class EpisodeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = EpisodeItemBinding.bind(view)
        fun bind(item: Episode) = with(binding){
            tvName.text = item.name
            tvAirDateEdit.text = item.airDate
            tvEpisodeMarkEdit.text = item.episode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        collection.let{
            val item = it?.get(position)
            if (item != null) {
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int = collection?.size?: 0

}