package com.example.valommunityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val logInButton : Button = findViewById(R.id.logInButton)
        val emailtxt : TextView = findViewById(R.id.emailEditText)
        val passtxt : TextView = findViewById(R.id.passwordEditText)
        val regiterButton : TextView = findViewById(R.id.signUpButton)
        val recordarButton : TextView = findViewById(R.id.btnOlvidar)
        firebaseAuth = Firebase.auth
        logInButton.setOnClickListener()
        {
            signIn(emailtxt.text.toString(), passtxt.text.toString())
        }
        regiterButton.setOnClickListener {
            val i = Intent(this, AccountCreatorActivity::class.java)
            startActivity(i)
        }
        recordarButton.setOnClickListener {
            val i = Intent(this, RecordarPassActivity::class.java)
            startActivity(i)
        }
    }
    private fun signIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val verifica = user?.isEmailVerified
                    if (verifica==true) {
                        Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                        val i = Intent(this, HomeActivity::class.java)
                        startActivity(i)
                    }else{
                        Toast.makeText(baseContext, "No ha verificado el email.", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(baseContext,"ERROR", Toast.LENGTH_SHORT).show()
                }
            }
    }
}