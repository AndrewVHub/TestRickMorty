package com.example.rickmorty.presentation.ui.location

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.rickmorty.databinding.FragmentLocationBinding
import com.example.rickmorty.presentation.ui.base.BaseFragment
import com.example.rickmorty.presentation.ui.location.adapters.LocationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocationFragment : BaseFragment<FragmentLocationBinding>(FragmentLocationBinding::inflate) {

    private val viewModel: LocationViewModel by viewModel()
    private val adapter = LocationAdapter()

    override fun FragmentLocationBinding.initialize() {

        recyclerLocation.adapter = adapter

        viewModel.searchList.observe(viewLifecycleOwner){data ->
            adapter.collection = data
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.load()
            context?.let { hideKeyboard(it, requireView()) }
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

        //Search Events place
        etSearch.addTextChangedListener {
            viewModel.searchLocation(it.toString())
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



