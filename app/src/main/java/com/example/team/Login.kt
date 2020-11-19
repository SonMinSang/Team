package com.example.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_signup.setOnClickListener(){
            val intent = Intent(this, SignUp::class.java)
            //intent.putExtra("WMI",ed_ID.text.toString())
            startActivity(intent)
        }
    }
}