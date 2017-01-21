package com.github.kazy1991.remee.view.fragment

import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kazy1991.dependencykit.DependencyKit
import com.github.kazy1991.remee.R
import com.github.kazy1991.remee.adapter.FavoriteListAdapter
import com.github.kazy1991.twitterpack.TwitterPack
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class FavoriteListFragment : Fragment() {
    companion object {
        fun newInstance(): Fragment {
            return FavoriteListFragment()
        }
    }

    @Inject
    lateinit var twitter: TwitterPack

    val recyclerView by lazy { view?.findViewById(R.id.recycler_view) as RecyclerView }

    val adapter = object : FavoriteListAdapter() {
        override fun onClickOgpView(view: View, url: String) {
            CustomTabsIntent.Builder().build().apply {
                launchUrl(context, Uri.parse(url))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        DependencyKit.inject(this)
        val view = inflater?.inflate(R.layout.fragment_favorite_list, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        twitter
                .fetchFavorite("101kaz")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.addAll(it)
                    adapter.notifyDataSetChanged()
                }, {
                    it.cause
                })
    }
}
