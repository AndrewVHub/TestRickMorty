package com.example.rickmorty.presentation.ui.characters

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickmorty.databinding.FragmentCharacterBinding
import com.example.rickmorty.presentation.ui.base.BaseFragment
import com.example.rickmorty.presentation.ui.characters.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>(FragmentCharacterBinding::inflate) {


    private val viewModel: CharacterViewModel by viewModels()
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        downloadProcess?.invoke()
        downloadProcess = null
    }


    private var downloadProcess: (() -> Unit)? = null

    override fun FragmentCharacterBinding.initialize() {

        val adapter = CharacterAdapter{ url, name->
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
                downloadProcess = {
                    saveOnDevice(url, name)
                }
                requestPermissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }else{
                saveOnDevice(url, name)
            }
        }
        recyclerCharacter.adapter = adapter

        viewModel.searchList.observe(viewLifecycleOwner){data ->
            adapter.collection = data
        }

        toolbar.setNavigationOnClickListener {
            //Back to HomeFragment
            findNavController().popBackStack()
        }
        swipeRefresh.setOnRefreshListener {
            viewModel.load()
            hideKeyboard(requireContext(), requireView())
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