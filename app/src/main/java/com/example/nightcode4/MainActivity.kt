package com.example.nightcode4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.example.nightcode4.databinding.ActivityMainBinding
import com.example.nightcode4.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


public val fbUrl = "https://nightcode4-2a2e6-default-rtdb.europe-west1.firebasedatabase.app/"


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        addListenerForMenu()
        supportFragmentManager.beginTransaction()
            .replace(binding.frameMain.id, TestsFragment())
            .commit()


    }

    private fun addListenerForMenu() {
        binding.bottomNav.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.menu_tasks -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.frameMain.id, TestsFragment())
                        .commit()
                    true
                }
                R.id.menu_lec -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.frameMain.id, ChooseLecFragment())
                        .commit()

                    true
                }
                R.id.menu_favourites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.frameMain.id, FavouritesFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }



}