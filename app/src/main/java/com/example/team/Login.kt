package com.example.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.team.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, Loading::class.java)
        startActivity(intent)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.btSignin.setOnClickListener{

        }
        binding.btSignup.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            //intent.putExtra("WMI",ed_ID.text.toString())
            startActivity(intent)

        }

    }
}