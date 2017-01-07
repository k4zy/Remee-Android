package com.github.kazy1991.remee.view.activity

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM
import android.view.SubMenu
import com.github.kazy1991.remee.R
import com.github.kazy1991.remee.model.DrawerItem
import com.github.kazy1991.remee.presenter.MainPresenter
import com.github.kazy1991.remee.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    val typeface by lazy { Typeface.createFromAsset(assets, "fontawesome-webfont.ttf") }

    val navigationView by lazy { findViewById(R.id.navigation) as NavigationView }

    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this)
        mainPresenter.onCreate()
    }

    override fun setupChannelMenu(items: List<DrawerItem>) {
        navigationView.menu?.addSubMenu(R.string.channel_menu_title)?.let {
            setupSubMenu(it, items)
        }
    }

    override fun setupOthersMenu(items: List<DrawerItem>) {
        navigationView.menu?.addSubMenu(R.string.others_menu_title)?.let {
            setupSubMenu(it, items)
        }
    }

    fun setupSubMenu(subMenu: SubMenu, items: List<DrawerItem>) {
        items.forEach {
            subMenu.add(Menu.NONE, Menu.NONE, Menu.NONE, it.title)?.apply {
                setShowAsAction(SHOW_AS_ACTION_IF_ROOM)
                icon = it.createIcon(this@MainActivity, typeface)
            }
        }
    }

}

