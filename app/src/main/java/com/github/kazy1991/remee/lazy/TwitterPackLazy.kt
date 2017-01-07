package com.github.kazy1991.remee.lazy

import android.content.Context

import com.github.kazy1991.dependencykit.Lazy
import com.github.kazy1991.remee.BuildConfig
import com.github.kazy1991.twitterpack.TwitterAuthConfig
import com.github.kazy1991.twitterpack.TwitterPack
import com.github.kazy1991.twitterpack.TwitterPackImpl


class TwitterPackLazy(private val context: Context) : Lazy<TwitterPack> {

    private fun config(): TwitterAuthConfig {
        return TwitterAuthConfig(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET)
    }

    override fun get(): TwitterPack {
        return TwitterPackImpl(context, config())
    }
}
