package com.github.kazy1991.remee.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kazy1991.remee.R


class FavoriteListFragment : Fragment() {
    companion object {
        fun newInstance(): Fragment {
            return FavoriteListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_favorite_list, container, false)
        return view
    }
}
