package com.example.team.Fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.team.R
import com.google.firebase.ktx.Firebase


class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

//        val database = Firebase.database
//        val myRef = database.getReference("message")
    }
}