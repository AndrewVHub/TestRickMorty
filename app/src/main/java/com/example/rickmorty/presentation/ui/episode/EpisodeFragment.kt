package com.example.rickmorty.presentation.ui.episode

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickmorty.databinding.FragmentEpisodeBinding
import com.example.rickmorty.presentation.ui.base.BaseFragment
import com.example.rickmorty.presentation.ui.episode.adapters.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>(FragmentEpisodeBinding::inflate) {

    private val viewModel: EpisodeViewModel by viewModels()
    private val adapter = EpisodeAdapter()
    override fun FragmentEpisodeBinding.initialize() {

        recyclerEpisode.adapter = adapter

        viewModel.searchList.observe(viewLifecycleOwner) { data ->
            adapter.collection = data
        }

        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.load()
            context?.let { hideKeyboard(it, requireView()) }
            etSearch.text.clear()

        }

        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is EpisodeViewModel.Action.HideLoader -> swipeRefresh.isRefreshing = false
                is EpisodeViewModel.Action.ShowError -> Toast.makeText(
                    context,
                    action.errorMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        //Search Events place
        etSearch.addTextChangedListener {
            viewModel.searchEpisode(it.toString())
        }
        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                context?.let { hideKeyboard(it, requireView()) }
            }
            true
        }
        //End Search Events place
    }

    private fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}