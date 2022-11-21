package com.example.logo

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.logo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment, R.id.favoriteFragment, R.id.cardFragment, R.id.profileFragment
        ))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        supportActionBar!!.hide()



//        val url = "http://10.0.2.2:80/api/catalog/product/list"
//
//        AsynTaskHandleJson().execute(url)

    }

//    inner class AsynTaskHandleJson: AsyncTask<String, String, String>(){
//        override fun doInBackground(vararg url: String?): String{
//
//            var text: String
//            val connection = URL(url[0]).openConnection() as HttpURLConnection
//            try {
//                connection.connect()
//                text = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
//            }finally {
//                connection.disconnect()
//            }
//
//            Log.d("test", "$text")
//            return text
//        }
//    }
}