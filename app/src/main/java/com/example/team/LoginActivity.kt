package com.example.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    var auth : FirebaseAuth? =null
    var googleSigninClient : GoogleSignInClient?=null
    var GOOGLE_LOGIN_CODE = 9001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
//        email_login.setOnClickListener{
//            signinAndSignup()
//        }
        google_sign_in_button.setOnClickListener{
            googleLogin()
        }
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSigninClient = GoogleSignIn.getClient(this,gso)
    }
    fun googleLogin(){
        var signInIntent = googleSigninClient?.signInIntent
        startActivityForResult(signInIntent,GOOGLE_LOGIN_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_LOGIN_CODE){
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result != null) {
                if(result.isSuccess){
                    var account = result?.signInAccount
                    firebaseAuthWithGoogle(account)
                }
            }
        }
    }
    fun firebaseAuthWithGoogle(account: GoogleSignInAccount?){
        var credential = GoogleAuthProvider.getCredential(account?.idToken,null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //아이디 맞았을때
                    moveMainPage(task.result?.user)
                } else {
                    Toast.makeText(this,task.exception?.message, Toast.LENGTH_LONG).show()
                    //x --> error meassage
                }
            }
    }
//    fun signinAndSignup(){
//        auth?.createUserWithEmailAndPassword(email_edittext.text.toString(),password_edittext.text.toString())
//            ?.addOnCompleteListener{
//                    task ->
//                if(task.isSuccessful){
//                    //creating a user account
//                    moveMainPage(task.result?.user)
//                }else if(!task.exception?.message.isNullOrEmpty()){
//                    //Show error message
//                    Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
//                }else{
//                    //Login if you have account
//                    signinEmail()
//                }
//            }
//    }

//    fun signinEmail(){
//        auth?.signInWithEmailAndPassword(email_edittext.text.toString(),password_edittext.text.toString())
//            ?.addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    //아이디 맞았을때
//                    moveMainPage(task.result?.user)
//                } else {
//                    Toast.makeText(this,task.exception?.message, Toast.LENGTH_LONG).show()
//                    //x --> error meassage
//                }
//            }
//    }

    fun moveMainPage(user:FirebaseUser?){
        if(user != null){
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}

