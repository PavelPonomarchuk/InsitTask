package ru.ponomarchukpn.insittask.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.ponomarchukpn.insittask.R
import ru.ponomarchukpn.insittask.databinding.FragmentCreateTodoBinding
import ru.ponomarchukpn.insittask.di.AppComponent
import ru.ponomarchukpn.insittask.domain.entity.CreateTodoStatus
import ru.ponomarchukpn.insittask.presentation.core.BaseFragment
import ru.ponomarchukpn.insittask.presentation.viewmodel.CreateTodoViewModel
import ru.ponomarchukpn.insittask.utils.showToast

class CreateTodoFragment : BaseFragment<FragmentCreateTodoBinding, CreateTodoViewModel>(
    CreateTodoViewModel::class.java
){

    override fun createBinding(): FragmentCreateTodoBinding {
        return FragmentCreateTodoBinding.inflate(layoutInflater)
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonBackListener()
        setButtonSaveListener()
        subscribeViewModel()
    }

    private fun setButtonBackListener() {
        binding.editButtonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setButtonSaveListener() {
        binding.editButtonSave.setOnClickListener {
            tryCreateTodo()
        }
    }

    private fun tryCreateTodo() {
        val description = binding.editInput.text.toString()
        if (description.isNotBlank()) {
            viewModel.onButtonSavePressed(description)
        } else {
            val emptyDescriptionMessage = getString(R.string.app_empty_description_error)
            showToast(emptyDescriptionMessage)
        }
    }

    private fun subscribeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.createTodoState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { processCreationResult(it) }
        }
    }

    private fun processCreationResult(result: CreateTodoStatus) {
        when(result) {
            CreateTodoStatus.SUCCESS -> parentFragmentManager.popBackStack()
            CreateTodoStatus.EXISTS -> {
                val itemExistsMessage = getString(R.string.app_already_exists_error)
                showToast(itemExistsMessage)
            }
            CreateTodoStatus.UNAUTHORIZED -> parentFragmentManager.popBackStack()
            CreateTodoStatus.UNDEFINED_FAIL -> {
                val undefinedErrorMessage = getString(R.string.app_message_undefined_error)
                showToast(undefinedErrorMessage)
            }
        }
    }

    companion object {

        fun newInstance() = CreateTodoFragment()
    }
}