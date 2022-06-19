package com.example.rickmorty.presentation.ui.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.rickmorty.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        btnCharacter.setOnClickListener {
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToCharacterFragment())
        }
        btnEpisode.setOnClickListener {
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToEpisodeFragment())
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}