package ru.ponomarchukpn.insittask.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ponomarchukpn.insittask.presentation.screens.CreateTodoFragment
import ru.ponomarchukpn.insittask.presentation.screens.LoginFragment
import ru.ponomarchukpn.insittask.presentation.screens.MainFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        DomainModule::class,
        ViewModelsModule::class
    ]
)
interface AppComponent {

    fun inject(loginFragment: LoginFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(createTodoFragment: CreateTodoFragment)

    companion object {

        fun init(context: Context): AppComponent {
            return DaggerAppComponent.factory()
                .create(context)
        }
    }

    @Component.Factory
    interface AppComponentFactory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}