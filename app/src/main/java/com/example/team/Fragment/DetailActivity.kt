package com.example.team.Fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team.Adapter.HomeAdapter
import com.example.team.R
import com.example.team.data.Card
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home.*

class DetailActivity : AppCompatActivity() {
    val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myRef: DatabaseReference = db.getReference("timestamp")
    var auth: FirebaseAuth? = null
    val postId = intent.getIntExtra("postId",-1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }


}