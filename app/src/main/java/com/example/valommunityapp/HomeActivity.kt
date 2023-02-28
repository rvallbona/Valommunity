package com.example.valommunityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val signOutButton : Button = findViewById(R.id.signOutButton)
        signOutButton.setOnClickListener {
            firebaseAuth.signOut()
            val i = Intent(this, AuthActivity::class.java)
            startActivity(i)
            Toast.makeText(baseContext, "Sessión cerrada", Toast.LENGTH_SHORT).show()
        }
        firebaseAuth = Firebase.auth
    }

    override fun onBackPressed() {
        return
    }
}