package ru.ponomarchukpn.insittask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.ponomarchukpn.insittask.presentation.viewmodel.CreateTodoViewModel
import ru.ponomarchukpn.insittask.presentation.viewmodel.LoginViewModel
import ru.ponomarchukpn.insittask.presentation.viewmodel.MainViewModel
import ru.ponomarchukpn.insittask.presentation.viewmodel.ViewModelFactory
import ru.ponomarchukpn.insittask.presentation.viewmodel.ViewModelKey

@Module
interface ViewModelsModule {

    @Binds
    fun bindViewModelFactory(
        viewModelFactory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateTodoViewModel::class)
    fun bindCreateTodoViewModel(createTodoViewModel: CreateTodoViewModel): ViewModel
}