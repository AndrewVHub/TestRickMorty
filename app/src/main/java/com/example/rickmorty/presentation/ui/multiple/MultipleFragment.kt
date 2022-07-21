package com.example.rickmorty.presentation.ui.multiple

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.rickmorty.databinding.FragmentMultipleBinding
import com.example.rickmorty.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MultipleFragment : BaseFragment<FragmentMultipleBinding>(FragmentMultipleBinding::inflate) {

    private val viewModel: MultipleViewModel by viewModels()
    private val adapter = MultipleAdapter()

    override fun FragmentMultipleBinding.initialize() {
        recyclerMultiple.adapter = adapter

        viewModel.searchList.observe(viewLifecycleOwner){data ->
            adapter.items = data
            Log.e("TAG", viewModel.searchList.value.toString())
        }
    }

}