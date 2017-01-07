package com.github.kazy1991.remee.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.kazy1991.dependencykit.DependencyKit;
import com.github.kazy1991.remee.module.RemeeModule;

public class RemeeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DependencyKit.configure(new RemeeModule(this));
        Fresco.initialize(this);
    }
}
