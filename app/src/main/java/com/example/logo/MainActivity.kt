package com.example.logo

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.logo.databinding.ActivityMainBinding
import com.example.logo.ui.goods.GoodsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeGraph, R.id.favoriteFragment, R.id.cartGraph, R.id.profileFragment
        ))

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when (destination.id){
                R.id.gallery -> {
                    navView.visibility = View.GONE
                    window?.setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN)
                    window!!.decorView.apply {
                        systemUiVisibility =
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
                    }
                }
                else -> {
                    navView.visibility = View.VISIBLE

                    window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                    window!!.decorView.apply {
                        systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    }
                }
            }
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        supportActionBar!!.hide()

    }
}