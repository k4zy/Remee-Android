package com.github.kazy1991.remee.module;

import android.content.Context;

import com.github.kazy1991.dependencykit.Module;
import com.github.kazy1991.remee.lazy.TwitterPackLazy;
import com.github.kazy1991.twitterpack.TwitterPack;


public class RemeeModule extends Module {
    private final Context context;

    public RemeeModule(Context context) {
        this.context = context;
        configure();
    }

    @Override
    protected void configure() {
        bind(TwitterPack.class).to(new TwitterPackLazy(context));
    }
}
