package com.example.valommunityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.valommunityapp.Fragments.AddPublicationFragment
import com.example.valommunityapp.Fragments.HomeFragment
import com.example.valommunityapp.Fragments.PerfilFragment
import com.example.valommunityapp.databinding.MainHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding : MainHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_home)
        binding = MainHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.addpublication -> replaceFragment(AddPublicationFragment())
                R.id.profile -> replaceFragment(PerfilFragment())
                else -> {
                }
            }
            true
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu_up, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_search -> {
                Toast.makeText(baseContext, "Buscar usuario.", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_exit -> {
                signOut()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
    private fun signOut(){
        firebaseAuth.signOut()
        val i = Intent(this, AuthActivity::class.java)
        startActivity(i)
}
    override fun onBackPressed() {
        return
    }
}