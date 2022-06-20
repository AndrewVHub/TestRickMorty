package com.example.rickmorty.presentation.ui.location

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
import com.example.rickmorty.databinding.FragmentLocationBinding
import com.example.rickmorty.presentation.ui.location.adapters.LocationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : Fragment() {

    private lateinit var binding: FragmentLocationBinding
    private val viewModel: LocationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        val adapterLocation = LocationAdapter()
        recyclerLocation.adapter = adapterLocation
        viewModel.searchList.observe(viewLifecycleOwner){data ->
            adapterLocation.collection = data
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.load()
            context?.let { hideKeyboard(it, view) }
            etSearch.text.clear()

        }

        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is LocationViewModel.Action.HideLoader -> swipeRefresh.isRefreshing = false
                is LocationViewModel.Action.ShowError -> Toast.makeText(
                    context,
                    action.errorMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        ivBackRow.setOnClickListener {
            Navigation.findNavController(view).navigate(LocationFragmentDirections.actionLocationFragmentToHomeFragment())
        }

        //Search Events place
        etSearch.addTextChangedListener {
            viewModel.searchLocation(it.toString())
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
        binding = FragmentLocationBinding.inflate(inflater, container, false)
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