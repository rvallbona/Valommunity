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

class AccountCreatorActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_creator)
        val newname : TextView = findViewById(R.id.nameEditText)
        val newemail : TextView = findViewById(R.id.emailEditText)
        val newpassword : TextView = findViewById(R.id.passwordEditText)
        val newrepassword : TextView = findViewById(R.id.repasswordEditText)
        val createAccountButton : Button = findViewById(R.id.newsignUpButton)
        createAccountButton.setOnClickListener {
            var pass1 = newpassword.text.toString()
            val pass2 = newrepassword.text.toString()
            if (pass1.equals(pass2)){
                createAccount(newemail.text.toString(), newpassword.text.toString())
            }else{
                Toast.makeText(baseContext, "ERROR, passwords no coinciden", Toast.LENGTH_SHORT).show()
                newpassword.requestFocus()
            }
        }

        firebaseAuth = Firebase.auth
    }
    private  fun createAccount(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    Toast.makeText(baseContext, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(baseContext, "Algo salió mal, ERROR: " + task.exception, Toast.LENGTH_SHORT).show()
                }
            }
    }
}