package com.nwt.amphibians

import android.app.Application
import com.nwt.amphibians.data.AppContainer
import com.nwt.amphibians.data.ConcreteAppContainer

class AmphibiansApplication : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = ConcreteAppContainer()
    }
}