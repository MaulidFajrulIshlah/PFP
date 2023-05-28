package com.geminiboy.pfp.view.loginregist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.geminiboy.pfp.R
import com.geminiboy.pfp.databinding.FragmentLoginBinding
import com.geminiboy.pfp.model.users.SignInBody
import com.geminiboy.pfp.view.MainActivity
import com.geminiboy.pfp.view.uiutil.LoadingDialog
import com.geminiboy.pfp.viewmodel.LoginViewModel
import com.geminiboy.pfp.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null
    private val loginVM: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loading = LoadingDialog(requireActivity())
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        loginVM.loginResult.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    loading.startLoading()
                }
                is Resource.Success -> {
                    loading.dismissLoading()
                    showErrorToast("Login Berhasil")
                    navigateToHome()
                }
                is Resource.Error -> {
                    loading.dismissLoading()
                    showErrorToast(resource.message)
                }
            }
        }

        binding?.txtBpa?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        binding?.btnLogin?.setOnClickListener {
            val email = binding?.insEmail?.text.toString()
            val password = binding?.insPass?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val signInBody = SignInBody(email, password)
                loginVM.authLogin(signInBody)
            } else {
                showErrorToast("Email dan password harus diisi")
            }
        }
    }

    private fun navigateToHome(){
        findNavController().navigate(R.id.action_loginFragment_to_fragmentHome)
    }

    private fun showErrorToast(errorMessage: String?) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }
}