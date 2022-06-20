package com.example.rickmorty.presentation.ui.episode

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import com.example.rickmorty.databinding.FragmentEpisodeBinding
import com.example.rickmorty.presentation.ui.episode.adapters.EpisodeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment() {

    private lateinit var binding: FragmentEpisodeBinding
    private val viewModel: EpisodeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val adapterEpisode = EpisodeAdapter()
        recyclerEpisode.adapter = adapterEpisode
        viewModel.searchList.observe(viewLifecycleOwner){data ->
            adapterEpisode.collection = data
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.load()
            context?.let { hideKeyboard(it, view) }
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

        ivBackRow.setOnClickListener {
            Navigation.findNavController(view).navigate(EpisodeFragmentDirections.actionEpisodeFragmentToHomeFragment())
        }

        //Search Events place
        etSearch.addTextChangedListener {
            viewModel.searchCharacter(it.toString())
        }
        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                context?.let { hideKeyboard(it, view) }
            }
            true
        }
        //End Search Events place
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.transactionAllData()
    }

    private fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}