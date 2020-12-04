package com.example.team

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.team.Fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.navi_home -> {
                var detailViewFragment = DetailViewFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailViewFragment).commit()
                return true
            }
            R.id.navi_map -> {
                var mapFragment = MapFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, mapFragment).commit()
                return true
            }
            R.id.navi_add -> {
                if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    startActivity(Intent(this, AddPhotoActivity::class.java))
                }

                return true
            }
            R.id.navi_chat -> {
                var chatFragment = ChatFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, chatFragment).commit()
                return true
            }
            R.id.navi_user -> {
                var userFrament = ProfileFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, userFrament).commit()
                return true
            }
        }
        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(this)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
    }
}