package com.alsharany.mydrawer

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var draweLayout:DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var navigationView:NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        draweLayout=findViewById(R.id.drawer_layout)
        toolbar=findViewById(R.id.toolbar)
        navigationView=findViewById(R.id.nav_view)
        setSupportActionBar(toolbar)
        val fragment: Fragment =CategoryFragment.newInstance(0,"Home",Color.YELLOW)
        setFragment(fragment)
        val actionBarDrawerToggle=ActionBarDrawerToggle(
            this,
            draweLayout,
            toolbar,
            R.string.open_drawer_content_des,
            R.string.close_drawer_content_des
        )
        val menu=navigationView.menu


        navigationView.bringToFront()
        draweLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        //navigationView.setCheckedItem(menu.findItem(R.id.home))

    }

    override fun onBackPressed() {
        if(draweLayout.isDrawerOpen(GravityCompat.START)){
            draweLayout.closeDrawer(GravityCompat.START)
        }
        else
        super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val category=Category(item.itemId,item.title.toString())
        val color =when(item.itemId){
            R.id.home->Color.RED
            R.id.food->Color.BLUE
            R.id.drink->Color.GREEN
            R.id.dessert->Color.YELLOW
            else->Color.CYAN
        }
            val fragment: Fragment =CategoryFragment.newInstance(category.id,category.name,color)
        toolbar.title=category.name

        when(item.itemId){
            R.id.home->{}
            R.id.about_me->{
                setFragment(AboutMeFrament())
            }
            else -> setFragment(fragment)

        }
        draweLayout.closeDrawer(GravityCompat.START)
        return true

    }
    fun setFragment(fragment: Fragment){
        val fragmentManager= supportFragmentManager
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.contaner,fragment).addToBackStack(null).commit()
    }
}