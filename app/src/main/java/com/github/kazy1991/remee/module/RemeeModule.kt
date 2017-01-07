package com.github.kazy1991.remee.module

import android.content.Context

import com.github.kazy1991.dependencykit.Module
import com.github.kazy1991.remee.lazy.TwitterPackLazy
import com.github.kazy1991.twitterpack.TwitterPack


class RemeeModule(private val context: Context) : Module() {

    init {
        configure()
    }

    override fun configure() {
        bind(TwitterPack::class.java).to(TwitterPackLazy(context))
    }
}
