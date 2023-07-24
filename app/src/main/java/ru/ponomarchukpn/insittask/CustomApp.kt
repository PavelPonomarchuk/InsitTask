package ru.ponomarchukpn.insittask

import android.app.Application
import ru.ponomarchukpn.insittask.di.App
import ru.ponomarchukpn.insittask.di.AppComponent

class CustomApp : Application(), App {

    private var appComponent: AppComponent? = null

    override fun appComponent() = getAppComponent()

    private fun getAppComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = AppComponent.init(applicationContext)
        }
        return appComponent!!
    }
}