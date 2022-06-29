package com.example.rickmorty.presentation.ui.home

import androidx.navigation.fragment.findNavController
import com.example.rickmorty.databinding.FragmentHomeBinding
import com.example.rickmorty.presentation.ui.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun FragmentHomeBinding.initialize() {
        btnCharacter.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCharacterFragment())
        }
        btnEpisode.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEpisodeFragment())
        }
        btnLocation.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLocationFragment())
        }
    }

}