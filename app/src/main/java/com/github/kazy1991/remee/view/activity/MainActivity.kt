package com.github.kazy1991.remee.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.kazy1991.dependencykit.DependencyKit
import com.github.kazy1991.remee.R
import com.github.kazy1991.twitterpack.TwitterPack
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var twitter: TwitterPack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DependencyKit.inject(this)
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
