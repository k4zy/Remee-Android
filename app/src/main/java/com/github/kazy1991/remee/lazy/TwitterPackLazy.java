package com.github.kazy1991.remee.lazy;

import android.content.Context;

import com.github.kazy1991.dependencykit.Lazy;
import com.github.kazy1991.remee.BuildConfig;
import com.github.kazy1991.remee.dummy.DummyTwitterPack;
import com.github.kazy1991.twitterpack.TwitterAuthConfig;
import com.github.kazy1991.twitterpack.TwitterPackImpl;


public class TwitterPackLazy implements Lazy {
    private final Context context;

    public TwitterPackLazy(Context context) {
        this.context = context;
    }

    private TwitterAuthConfig config() {
        return new TwitterAuthConfig(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET);
    }

    @Override
    public Object get() {
        return new DummyTwitterPack(context);
    }
}
