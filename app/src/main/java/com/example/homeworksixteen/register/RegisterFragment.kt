package com.example.homeworksixteen.register

import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworksixteen.BaseFragment
import com.example.homeworksixteen.R
import com.example.homeworksixteen.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    private val viewModel: RegisterViewModel by viewModels()


    override fun setUp() {

    }

    override fun listeners() {
        binding.btnRegister.setOnClickListener {


            if (inputValidation(binding.etEmail) && inputValidation(binding.etUsername) && inputValidation(
                    binding.etPassword
                ) && emailValidation(binding.etEmail)
            ) {
                viewModel.register(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
                bindObserves()
            }


        }


    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registrationFlow.collect {
                    when (it) {
                        is RegistrationState.Loading -> {
                            binding.pbRegister.visibility = View.VISIBLE
                        }

                        is RegistrationState.Success -> {
                            val registeredUser = it.responseRegister
                            binding.pbRegister.visibility = View.GONE
                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            openLogin()
                        }

                        is RegistrationState.Failed -> {
                            binding.pbRegister.visibility = View.GONE
                            val errorMessage = it.errorMessage
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
                }
            }
        }
    }

    private fun openLogin() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLogInFragment()
        findNavController().navigate(action)
    }


}