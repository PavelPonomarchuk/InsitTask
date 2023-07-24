package ru.ponomarchukpn.insittask.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.ponomarchukpn.insittask.R
import ru.ponomarchukpn.insittask.databinding.FragmentMainBinding
import ru.ponomarchukpn.insittask.di.AppComponent
import ru.ponomarchukpn.insittask.domain.entity.LoginStatus
import ru.ponomarchukpn.insittask.domain.entity.TodoEntity
import ru.ponomarchukpn.insittask.presentation.adapter.TodoAdapter
import ru.ponomarchukpn.insittask.presentation.core.BaseFragment
import ru.ponomarchukpn.insittask.presentation.viewmodel.MainViewModel
import ru.ponomarchukpn.insittask.utils.gone
import ru.ponomarchukpn.insittask.utils.visible

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(
    MainViewModel::class.java
) {

    private val adapter by lazy {
        TodoAdapter(
            onItemLongClick = {
                viewModel.onItemLongClicked(it)
                true
            }
        )
    }

    override fun createBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideViews()
        setAdapter()
        setButtonCreateListener()
        subscribeViewModel()
        viewModelStart()
    }

    override fun onResume() {
        super.onResume()
        viewModelStart()
    }

    private fun viewModelStart() {
        viewModel.onStartWorking()
    }

    private fun setAdapter() {
        binding.todoListRecycler.adapter = adapter
    }

    private fun setButtonCreateListener() {
        binding.todoListButtonAdd.setOnClickListener {
            launchCreateFragment()
        }
    }

    private fun subscribeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginStatus
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { processLoginStatus(it) }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.todoList
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { processTodoList(it) }
        }
    }

    private fun processLoginStatus(loginStatus: LoginStatus) {
        if (loginStatus == LoginStatus.AUTHORIZED) {
            viewModel.onUserAuthorized()
            showViews()
        } else {
            launchLoginFragment()
        }
    }

    private fun processTodoList(todos: List<TodoEntity>?) {
        todos?.let {
            adapter.submitList(it)
        }
    }

    private fun hideViews() {
        binding.todoListHeader.gone()
        binding.todoListRecycler.gone()
        binding.todoListButtonAdd.gone()
    }

    private fun showViews() {
        binding.todoListHeader.visible()
        binding.todoListRecycler.visible()
        binding.todoListButtonAdd.visible()
    }

    private fun launchLoginFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, LoginFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun launchCreateFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, CreateTodoFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {

        fun newInstance() = MainFragment()
    }
}