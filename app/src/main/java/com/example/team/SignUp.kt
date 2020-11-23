package com.example.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        bt_start.setOnClickListener(){
            val intent = Intent(this, Home_test::class.java)
            startActivity(intent)

        }

    }
    fun createID(){
        var ID=ed_ID.text.toString()
        var password=ed_NPW.text.toString()
        var password_2 = ed_NPW2.text.toString()
        if (password==password_2){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(ID,password).addOnCompleteListener{task ->
            if(task.isSuccessful){
                //pass to the next page
            }
        }
        }
    }
}

