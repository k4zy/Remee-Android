package com.github.kazy1991.remee.view


import com.github.kazy1991.remee.model.DrawerItem

interface MainView {

    fun setupChannelMenu(items: List<DrawerItem>)

    fun setupOthersMenu(items: List<DrawerItem>)
}
