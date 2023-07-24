package ru.ponomarchukpn.insittask.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.ponomarchukpn.insittask.R
import ru.ponomarchukpn.insittask.databinding.FragmentLoginBinding
import ru.ponomarchukpn.insittask.di.AppComponent
import ru.ponomarchukpn.insittask.presentation.core.BaseFragment
import ru.ponomarchukpn.insittask.presentation.viewmodel.LoginViewModel
import ru.ponomarchukpn.insittask.utils.showToast

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    LoginViewModel::class.java
){

    override fun createBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()
        setButtonLoginListener()
    }

    private fun subscribeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { processLoginResult(it) }
        }
    }

    private fun processLoginResult(success: Boolean) {
        if (success) {
            parentFragmentManager.popBackStack()
        } else {
            val errorMessage = getString(R.string.app_message_undefined_error)
            showToast(errorMessage)
        }
    }

    private fun setButtonLoginListener() {
        binding.loginButton.setOnClickListener {
            tryLogin()
        }
    }

    private fun tryLogin() {
        val username = binding.loginInputName.text.toString()
        if (username.isNotBlank()) {
            viewModel.onLoginPressed(username)
        } else {
            val needNotEmptyUsernameMessage = getString(R.string.app_empty_username_error)
            showToast(needNotEmptyUsernameMessage)
        }
    }

    companion object {

        fun newInstance() = LoginFragment()
    }
}