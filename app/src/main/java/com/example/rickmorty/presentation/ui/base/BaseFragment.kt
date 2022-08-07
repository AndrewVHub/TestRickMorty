package com.example.rickmorty.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.rickmorty.presentation.utils.DownloadImage
import javax.inject.Inject


abstract class BaseFragment<B: ViewBinding>(
    private val inflateMethod : (LayoutInflater, ViewGroup?, Boolean) -> B
): Fragment() {

    private var _binding: B? = null
    private val binding get() = _binding!!
    @Inject lateinit var downloadImage: DownloadImage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateMethod.invoke(inflater, container, false)
        binding.initialize()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun saveOnDevice(image: String, fileName: String){
        downloadImage.saveOnDevice(image, fileName)
    }

    abstract fun B.initialize()
}