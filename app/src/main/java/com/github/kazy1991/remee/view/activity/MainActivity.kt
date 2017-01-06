package com.github.kazy1991.remee.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.kazy1991.remee.BuildConfig
import com.github.kazy1991.remee.R
import com.github.kazy1991.twitterpack.TwitterAuthConfig
import com.github.kazy1991.twitterpack.TwitterPackImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val twitter by lazy {
        TwitterPackImpl(this, authConfig)
    }

    val authConfig by lazy {
        TwitterAuthConfig(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.text_view).setOnClickListener {
            twitter
                    .fetchFavorite("101kaz")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        it.size
                    }, {
                        it.cause
                    })
        }
    }
}
