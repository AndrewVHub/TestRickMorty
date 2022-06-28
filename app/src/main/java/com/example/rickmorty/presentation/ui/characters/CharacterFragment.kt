package com.example.rickmorty.presentation.ui.characters

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.rickmorty.databinding.FragmentCharacterBinding
import com.example.rickmorty.presentation.ui.base.BaseFragment
import com.example.rickmorty.presentation.ui.characters.adapters.CharacterAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterFragment : BaseFragment<FragmentCharacterBinding>(FragmentCharacterBinding::inflate) {

    private val adapter = CharacterAdapter()
    private val viewModel: CharacterViewModel by viewModel()

    override fun FragmentCharacterBinding.initialize() {

        recyclerCharacter.adapter = adapter

        viewModel.searchList.observe(viewLifecycleOwner){data ->
            adapter.collection = data
        }

//        toolbar.setNavigationOnClickListener {
//            findNavController(requireView()).navigate()
//        }
        swipeRefresh.setOnRefreshListener {
            viewModel.load()
            context?.let { hideKeyboard(it, requireView()) }
            etSearch.text.clear()

        }

        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is CharacterViewModel.CharacterAction.HideLoader -> swipeRefresh.isRefreshing = false
                is CharacterViewModel.CharacterAction.ShowError -> Toast.makeText(
                    context,
                    action.errorMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        //Search Events place
        etSearch.addTextChangedListener {
            viewModel.searchCharacter(it.toString())
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