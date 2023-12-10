package com.example.homeworksixteen.login

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworksixteen.BaseFragment
import com.example.homeworksixteen.databinding.FragmentLogInBinding
import com.example.homeworksixteen.register.RegisterFragmentDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {


    private val viewmodel: LogInViewModel by viewModels()


    override fun setUp() {

    }

    override fun listeners() {
        binding.btnLogIn.setOnClickListener {

            if(inputValidation(binding.etUsername) && inputValidation(binding.etPassword)){
                viewmodel.logIn("eve.holt@reqres.in", binding.etPassword.text.toString())
                bindObserves()
            }
        }

    }

    override fun bindObserves() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.logInFlow.collect() {
                    when (it) {
                        is LogInState.Loading -> {
                            binding.pbLogIn.visibility = View.VISIBLE
                        }

                        is LogInState.Success -> {
                            val activeUser = it.responseLogIn
                            binding.pbLogIn.visibility = View.GONE
                            Toast.makeText(requireContext(), "log in success", Toast.LENGTH_SHORT)
                                .show()
                            openProfile()
                        }

                        is LogInState.Failed -> {
                            binding.pbLogIn.visibility = View.GONE
                            val errorMessage = it.errorMessage
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }

    private fun openProfile() {
        val action =LogInFragmentDirections.actionLogInFragmentToProfileFragment()
        findNavController().navigate(action)
    }





}