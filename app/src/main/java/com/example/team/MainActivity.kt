package com.example.team

//import com.example.team.Adapter.HomeAdapter
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team.Fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.navi_home -> {
                var detailViewFragment = DetailViewFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, detailViewFragment).commit()
                return true
            }
            R.id.navi_map -> {
                var mapFragment = MapFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, mapFragment).commit()
                return true
            }
            R.id.navi_add ->
                //권한 있는지 확인
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(Intent(this, AddPhotoActivity::class.java))
                }
//                else {
//                    Toast.makeText(this, "스토리지 읽기 권한이 없습니다.", Toast.LENGTH_LONG).show()
//                }


            R.id.navi_chat -> {
                var chatFragment = ChatFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, chatFragment).commit()
                return true
            }
            R.id.navi_user -> {
                var userFrament = UserFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, userFrament).commit()
                return true
            }
        }
        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        //set default screen
        bottom_navigation.selectedItemId = R.id.navi_home

//       // 로딩 화면
//        val intent = Intent(this, LoadingActivity::class.java)
//        startActivity(intent)

        //사진 권한
       ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)


    }

}