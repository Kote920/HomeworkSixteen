package com.example.homeworksixteen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


typealias inflater<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding>(private val inflate: inflater<VB>) : Fragment() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        listeners()

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun setUp()

    abstract fun listeners()

    abstract fun bindObserves()

     fun inputValidation(input: AppCompatEditText): Boolean {

        if (input.text.toString().trim().isNotEmpty()) {
            input.setBackgroundResource(R.drawable.button_grey)
            return true
        } else {
            input.setBackgroundResource(R.drawable.empty_text)
            return false
        }


    }

     fun emailValidation(input: AppCompatEditText): Boolean {
        if (input.text.toString() != "eve.holt@reqres.in") {
            input.setBackgroundResource(R.drawable.empty_text)
            Toast.makeText(requireContext(), "invalid Email!", Toast.LENGTH_SHORT).show()

            return false
        } else {
            input.setBackgroundResource(R.drawable.button_grey)

            return true

        }
    }

}