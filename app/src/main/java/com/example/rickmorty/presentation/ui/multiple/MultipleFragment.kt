package com.example.rickmorty.presentation.ui.multiple

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickmorty.R
import com.example.rickmorty.databinding.FragmentMultipleBinding
import com.example.rickmorty.presentation.ui.base.BaseFragment
import com.example.rickmorty.presentation.ui.location.LocationViewModel
import com.example.rickmorty.presentation.ui.location.adapters.LocationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MultipleFragment : BaseFragment<FragmentMultipleBinding>(FragmentMultipleBinding::inflate) {

    private val viewModel: MultipleViewModel by viewModel()
    private val adapter = MultipleAdapter()

    override fun FragmentMultipleBinding.initialize() {
        recyclerMultiple.adapter = adapter

        viewModel.searchList.observe(viewLifecycleOwner){data ->
            adapter.items = data
            Log.e("TAG", viewModel.searchList.value.toString())
        }
    }

}