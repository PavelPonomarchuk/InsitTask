package ru.ponomarchukpn.insittask.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
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