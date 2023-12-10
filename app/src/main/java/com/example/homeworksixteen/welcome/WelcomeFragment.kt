package com.example.homeworksixteen.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homeworksixteen.BaseFragment
import com.example.homeworksixteen.R
import com.example.homeworksixteen.databinding.FragmentWelcomeBinding

class WelcomeFragment :BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    override fun setUp() {

    }

    override fun listeners() {


        binding.btnLogIn.setOnClickListener {
            openLogInPage()
        }

        binding.btnRegister.setOnClickListener {
            openRegisterPage()
        }
    }

    override fun bindObserves() {

    }


    private fun openLogInPage(){
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToLogInFragment()
        findNavController().navigate(action)
    }

    private fun openRegisterPage(){
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
        findNavController().navigate(action)
    }


}