package com.example.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.team.databinding.ActivityLoginBinding
import com.example.team.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var auth : FirebaseAuth? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        val intent = Intent(this, Loading::class.java)
        startActivity(intent)

        binding.btSignup.setOnClickListener() {
            val intent = Intent(this, SignUp::class.java)
            //intent.putExtra("WMI",ed_ID.text.toString())
            startActivity(intent)
        }
        binding.btSignin.setOnClickListener() {
            auth!!.signInWithEmailAndPassword(binding.edID.text.toString(), binding.edPW.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, Home_test::class.java)
                        //intent.putExtra("WMI",ed_ID.text.toString())
                        startActivity(intent)
                    }
                }
        }
    }
}