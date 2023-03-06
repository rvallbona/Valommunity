package com.example.valommunityapp.LoginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.valommunityapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecordarPassActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordar_pass)
        val recoverEmail : TextView = findViewById(R.id.recoverEmail)
        val recoverPassButton : Button = findViewById(R.id.sendRecoverPass)
        recoverPassButton.setOnClickListener {
            sendPasswordReset(recoverEmail.text.toString())
        }
        firebaseAuth = Firebase.auth
    }
    private fun sendPasswordReset(email: String){
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(){ task ->
            if (task.isSuccessful){
                Toast.makeText(baseContext, "Correo de cambio de contraseña", Toast.LENGTH_SHORT).show()
                val i = Intent(this, AuthActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(baseContext, "ERROR: No se pudo completar el proceso", Toast.LENGTH_SHORT).show()
            }
        }
    }
}