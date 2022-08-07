package com.example.rickmorty.presentation.ui.characters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.data.models.character.CharacterModel
import com.example.rickmorty.databinding.CharacterItemBinding
import com.example.rickmorty.presentation.utils.load

class CharacterAdapter(
    private val onClick: (String, String) -> Unit
): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    var collection: List<CharacterModel>? = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = CharacterItemBinding.bind(view)
        fun bind(item: CharacterModel) = with(binding){
            tvName.text = item.name
            tvGenderEdit.text = item.gender
            tvLocationEdit.text = item.location.name
            tvSpeciesEdit.text = item.species
            ivImageItem.load(item.image)
            ivImageItem.setOnClickListener { onClick.invoke(item.image, item.name) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        collection.let{
            val item = it?.get(position)
            if (item != null) {
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int = collection?.size?: 0

}