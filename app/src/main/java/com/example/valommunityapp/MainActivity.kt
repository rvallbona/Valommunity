package com.example.valommunityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.valommunityapp.Fragments.AddPublicationFragment
import com.example.valommunityapp.Fragments.HomeFragment
import com.example.valommunityapp.Fragments.PerfilFragment
import com.example.valommunityapp.databinding.MainHomeBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : MainHomeBinding
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
    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
    override fun onBackPressed() {
        return
    }
}