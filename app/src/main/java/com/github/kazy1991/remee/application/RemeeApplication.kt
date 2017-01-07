package com.github.kazy1991.remee.application

import android.app.Application

import com.github.kazy1991.dependencykit.DependencyKit
import com.github.kazy1991.remee.module.RemeeModule

class RemeeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyKit.configure(RemeeModule(this))
    }
}
