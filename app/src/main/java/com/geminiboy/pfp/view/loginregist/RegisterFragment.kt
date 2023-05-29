package com.geminiboy.pfp.view.loginregist

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.geminiboy.pfp.R
import com.geminiboy.pfp.databinding.FragmentRegisterBinding
import com.geminiboy.pfp.view.MainActivity
import com.geminiboy.pfp.view.uiutil.LoadingDialog
import com.geminiboy.pfp.viewmodel.RegisterViewModel
import com.geminiboy.pfp.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var binding: FragmentRegisterBinding? = null
    private val registerVM: RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loading = LoadingDialog(requireActivity())
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        registerVM.registerResult.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    loading.startLoading()
                }
                is Resource.Success -> {
                    loading.dismissLoading()
                    showSuccessMessage()
                }
                is Resource.Error -> {
                    val errorMessage = resource.message
                    showErrorToast(errorMessage)
                }
            }
        }

        binding?.apply {
            RegistBtn.isEnabled = false

            RegistBtn.setOnClickListener {
                if (isInputValid()) {
                    val name = uss.text.toString()
                    val email = getEmail.text.toString()
                    val password = pass.text.toString()

                    registerVM.postUser(name, email, password)
                }
            }

            uss.addTextChangedListener {
                val username = it.toString()
                uss.error = when {
                    username.isEmpty() -> "Username tidak boleh kosong"
                    username.length < 6 -> "Username harus lebih dari 6 karakter"
                    else -> {
                        val isTaken = runBlocking { registerVM.isUsernameTaken(username) }
                        if (isTaken) "Username sudah terdaftar" else null
                    }
                }
                updateRegisterButtonState()
            }

            getEmail.addTextChangedListener {
                val email = it.toString()
                getEmail.error = when {
                    email.isEmpty() -> "Email tidak boleh kosong"
                    !isValidEmail(email) -> "Email tidak valid"
                    else -> {
                        val isTaken = runBlocking { registerVM.isEmailTaken(email) }
                        if (isTaken) "Email sudah terdaftar" else null
                    }
                }
                updateRegisterButtonState()
            }

            pass.addTextChangedListener {
                val password = it.toString()
                pass.error = when {
                    password.isEmpty() -> "Password tidak boleh kosong"
                    password.length < 6 -> "Password harus terdiri dari minimal 6 karakter"
                    else -> null
                }
                updateRegisterButtonState()
            }

            cPass.addTextChangedListener {
                val confirmPassword = it.toString()
                val password = pass.text.toString()

                cPass.error = when {
                    confirmPassword.isEmpty() -> "Konfirmasi password tidak boleh kosong"
                    confirmPassword != password -> "Konfirmasi password tidak sesuai dengan password"
                    else -> null
                }
                updateRegisterButtonState()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun isInputValid(): Boolean {
        val username = binding?.uss?.text.toString()
        val email = binding?.getEmail?.text.toString()
        val password = binding?.pass?.text.toString()
        val confirmPassword = binding?.cPass?.text.toString()

        return username.isNotEmpty() && binding?.uss?.error == null &&
                email.isNotEmpty() && binding?.getEmail?.error == null &&
                password.isNotEmpty() && binding?.pass?.error == null &&
                confirmPassword.isNotEmpty() && binding?.cPass?.error == null
    }


    private fun updateRegisterButtonState() {
        binding?.RegistBtn?.isEnabled = isInputValid()
    }

    private fun showSuccessMessage() {
        Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun showErrorToast(errorMessage: String?) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

}