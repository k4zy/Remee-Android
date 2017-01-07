package com.github.kazy1991.remee.view.activity

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM
import android.view.SubMenu
import android.widget.Toast
import com.github.kazy1991.remee.R
import com.github.kazy1991.remee.model.DrawerItem
import com.github.kazy1991.remee.presenter.MainPresenter
import com.github.kazy1991.remee.view.MainView
import com.github.kazy1991.remee.view.fragment.FavoriteListFragment

class MainActivity : AppCompatActivity(), MainView {

    val typeface by lazy { Typeface.createFromAsset(assets, "fontawesome-webfont.ttf") }

    val navigationView by lazy { findViewById(R.id.navigation) as NavigationView }

    val drawerLayout by lazy { findViewById(R.id.drawer_layout) as DrawerLayout }

    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this)
        mainPresenter.onCreate()
        setupNavigationViewClickListener()
    }

    fun setupNavigationViewClickListener() {
        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            when (it.groupId) {
                R.id.twitter_favorite -> {
                    Toast.makeText(this, "twitter fav", Toast.LENGTH_SHORT).show()
                    replaceFragment(FavoriteListFragment())
                    true
                }
                else -> {
                    Toast.makeText(this, "others", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun setupChannelMenu(items: List<DrawerItem>) {
        navigationView.menu?.addSubMenu(R.string.channel_menu_title)?.let {
            setupSubMenu(it, R.id.twitter_favorite, items)
        }
    }

    override fun setupOthersMenu(items: List<DrawerItem>) {
        navigationView.menu?.addSubMenu(R.string.others_menu_title)?.let {
            setupSubMenu(it, R.id.others, items)
        }
    }

    fun setupSubMenu(subMenu: SubMenu, groupId: Int, items: List<DrawerItem>) {
        items.forEach {
            subMenu.add(groupId, Menu.NONE, Menu.NONE, it.title)?.apply {
                setShowAsAction(SHOW_AS_ACTION_IF_ROOM)
                icon = it.createIcon(this@MainActivity, typeface)
            }
        }
    }

}

