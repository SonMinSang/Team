package com.example.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.team.Adapter.MainFragmentStatePagerAdapter
import com.example.team.Fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    private val mOnNavigationiItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {item ->

        when(item.itemId){
            R.id.navi_home -> {
                println("home pressed")
                replaceFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.navi_map -> {
                println("test pressed")
                replaceFragment(MapFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.navi_add -> {
                println("info pressed")
                replaceFragment(AddFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navi_chat -> {
                println("info pressed")
                replaceFragment(ChatFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navi_user -> {
                println("info pressed")
                replaceFragment(UserFragment())
                return@OnNavigationItemSelectedListener true
            }

            else -> false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        replaceFragment(HomeFragment())
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationiItemSelectedListener)
        //onfigureBottomNavigation()
    }
    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
//    private fun configureBottomNavigation(){
//        frag_page.adapter = MainFragmentStatePagerAdapter(supportFragmentManager, 5)
//
//        bottom_navi.setupWithViewPager(frag_page)
//
//        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.bottom_menu, null, false)
//
//        bottom_navi.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.navi_home) as RelativeLayout
//        bottom_navi.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.navi_map) as RelativeLayout
//        bottom_navi.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.navi_add) as RelativeLayout
//        bottom_navi.getTabAt(3)!!.customView = bottomNaviLayout.findViewById(R.id.navi_chat) as RelativeLayout
//        bottom_navi.getTabAt(4)!!.customView = bottomNaviLayout.findViewById(R.id.navi_user) as RelativeLayout
//
//
//
//    }

}

